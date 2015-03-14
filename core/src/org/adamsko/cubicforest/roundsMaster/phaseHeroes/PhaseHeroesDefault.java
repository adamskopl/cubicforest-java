package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjectsDefault;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tile.NullCubicTile;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tilePathsMaster.NullTilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearchersMaster;

public class PhaseHeroesDefault extends PhaseOrderableObjectsDefault implements
		PhaseHeroes {

	private final PhaseHeroesOrdersMaster heroesOrdersMaster;
	private final TilePathSearchersMaster tilePathSearchersMaster;
	private final EnemiesHelper enemiesHelper;
	private final GatherCubesMaster gatherCubesMaster;
	private final PrizesMaster prizesMaster;

	public PhaseHeroesDefault(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final TilesMaster tilesMaster, final OrdersMaster ordersMaster,
			final TilePathSearchersMaster tilePathSearchersMaster,
			final TilesLookController tilesLookController) {
		super(worldObjectsMastersContainer.getHeroesMaster(), ordersMaster,
				"PhaseHeroes");

		this.tilePathSearchersMaster = tilePathSearchersMaster;

		enemiesHelper = new EnemiesHelper(
				worldObjectsMastersContainer.getEnemiesMaster());

		heroesOrdersMaster = new PhaseHeroesOrdersMasterDefault(
				tilesLookController, tilesMaster,
				worldObjectsMastersContainer.getHeroesToolsMaster(),
				enemiesHelper);

		this.gatherCubesMaster = worldObjectsMastersContainer
				.getGatherCubesMaster();
		this.prizesMaster = worldObjectsMastersContainer.getPrizesMaster();

		initPlayerActionsHandler();
	}

	@Override
	public void initPlayerActionsHandler() {
		playerActionsHandler = new PlayerActionsHandlerPhaseHeroes(this);
	}

	@Override
	public PhaseHeroesOrdersMaster getOrdersMaster() {
		return heroesOrdersMaster;
	}

	@Override
	public HeroesToolsMaster getToolsMaster() {
		return heroesOrdersMaster.getHeroesToolsMaster();
	}

	@Override
	public void startPhase() {
		nextHero();
		getActivePlayer().makeNextDecision();
	}

	@Override
	public void onOrderFinished() {
		orderFinished();
		removeDeadObjects();

		if (roundsMaster.getGameResultMaster().isGameWon()
				|| victoryConditionsMet()) {
			getActivePlayer().onVictoryConditionsMet(
					prizesMaster.allPrizesCollected());
			roundsMaster.reload();
			roundsMaster.getGameResultMaster().resetGameResult();
			return;
		}

		if (isCurrentObjectLast()) {
			phaseIsOver(this);
			return;
		} else {
			// handle next object
			nextHero();
		}

		// player can make next decision
		getActivePlayer().makeNextDecision();
	}

	@Override
	public void orderStarted() {
		super.orderStarted();
	}

	@Override
	public void issueOrder(final WorldObject phaseObject,
			final TilePath tilePath) {
		ordersMaster.startOrder(getCurrentObject(), tilePath, this);
		setTilePathActive(NullTilePath.instance());
		getOrdersMaster().resetHighlight();
	}

	@Override
	public void nextHero() {
		nextObject();
		heroesOrdersMaster.setCurrentHero(getCurrentObject());
		heroesOrdersMaster.tilePicked(NullCubicTile.instance(), false);
		getOrdersMaster().highlightTilesOrder();
	}

	@Override
	public boolean victoryConditionsMet() {
		return noPhaseObjects();
	}

	@Override
	public boolean isPathOrderValidObject(final WorldObject phaseObject,
			final Tile tile, final TilePath pathToTile) {
		if (tile.isOccupied(phaseObject)) {
			return true;
		}

		if (pathToTile.length() == 0) {
			return false;
		}

		if (!tile.isTilePathSearchValid()) {
			return false;
		}

		if (!isOrderInProgress()
				&& pathToTile.length() - 1 <= phaseObject.getSpeed()) {
			return true;
		}

		return false;
	}

	@Override
	public TilePath searchTilePath(final WorldObject phaseObject,
			final Tile tile) {
		return tilePathSearchersMaster.getTilePathSearcherValidPath().search(
				getCurrentObject(), tile);
	}

	@Override
	public boolean isHeroToolAffordable(final WorldObjectType heroToolType) {
		return gatherCubesMaster.getGatherCubesCounter().isToolAffordable(
				heroToolType);
	}

}