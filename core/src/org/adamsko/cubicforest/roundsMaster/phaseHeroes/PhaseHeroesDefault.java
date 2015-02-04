package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjectsDefault;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
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

	public PhaseHeroesDefault(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final TilesMaster tilesMaster, final OrdersMaster ordersMaster,
			final TilePathSearchersMaster tilePathSearchersMaster,
			final TilesLookController tilesLookController) {
		super(worldObjectsMastersContainer.getHeroesMaster(), ordersMaster,
				tilesLookController, "PhaseHeroes");

		this.tilePathSearchersMaster = tilePathSearchersMaster;

		enemiesHelper = new EnemiesHelper(
				worldObjectsMastersContainer.getEnemiesMaster());

		heroesOrdersMaster = new PhaseHeroesOrdersMasterDefault(
				tilesLookController, tilesMaster,
				worldObjectsMastersContainer.getHeroesToolsMaster(),
				enemiesHelper);

		this.gatherCubesMaster = worldObjectsMastersContainer
				.getGatherCubesMaster();

		initPlayerActionsHandler();
	}

	@Override
	public void initPlayerActionsHandler() {
		playerActionsHandler = new PlayerActionsHandlerPhaseHeroes(this);
	}

	@Override
	public void startPhase() {
		nextHero();
		heroesOrdersMaster
				.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);

		getActivePlayer().makeNextDecision();
	}

	@Override
	public void onOrderFinished() {
		orderFinished();
		removeDeadObjects();

		if (roundsMaster.getGameResultMaster().isGameWon()
				|| victoryConditionsMet()) {
			getActivePlayer().onVictoryConditionsMet();
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
			heroesOrdersMaster
					.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);
		}

		// player can make next decision
		getActivePlayer().makeNextDecision();
	}

	@Override
	public void orderStarted() {
		super.orderStarted();
		heroesOrdersMaster
				.changePhaseHeroesMode(PhaseHeroesMode.MODE_ORDER_EXECUTION);
	}

	@Override
	public void issueOrder(final WorldObject phaseObject,
			final TilePath tilePath) {
		ordersMaster.startOrder(getCurrentObject(), tilePath, this);
		setTilePathActive(NullTilePath.instance());
	}

	@Override
	public void nextHero() {
		nextObject();
		heroesOrdersMaster.setCurrentHero(getCurrentObject());
	}

	@Override
	public boolean victoryConditionsMet() {
		return noPhaseObjects();
	}

	@Override
	public List<OrderDecisionDefault> getCurrentPossbileDecisions() {
		return heroesOrdersMaster.getCurrentPossbileDecisions();
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
	public boolean isHeroToolAffordable(final WorldObjectType heroToolType) {
		return gatherCubesMaster.getGatherCubesCounter().isToolAffordable(
				heroToolType);
	}

	@Override
	public void chooseHeroTool(final WorldObjectType heroToolType) {
		// change mode and also set marker's type
		heroesOrdersMaster.changePhaseHeroesMode(
				PhaseHeroesMode.MODE_CHOICE_TOOL, heroToolType);
	}

	@Override
	public boolean isHeroToolChosen() {
		return heroesOrdersMaster.getPhaseHeroesMode() == PhaseHeroesMode.MODE_CHOICE_TOOL;
	}

	@Override
	public TilePath searchTilePath(final WorldObject phaseObject,
			final Tile tile) {
		return tilePathSearchersMaster.getTilePathSearcherValidPath().search(
				getCurrentObject(), tile);
	}

	@Override
	public void highlightTilesOrder(final Tile tilePickedOrder,
			final Boolean tileOrderValid) {
		heroesOrdersMaster.highlightTilesOrder(tilePickedOrder, tileOrderValid);
	}

	@Override
	public void addHeroToolMarker(final Tile tileTool) {
		heroesOrdersMaster.tilePicked(tileTool, true);
	}

	@Override
	public void removeHeroToolMarker() {
		heroesOrdersMaster.removeHeroToolMarker();
	}

}