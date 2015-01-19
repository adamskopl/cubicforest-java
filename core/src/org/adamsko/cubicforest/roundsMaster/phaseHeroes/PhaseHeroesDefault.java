package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.mapsResolver.MapsResolver;
import org.adamsko.cubicforest.mapsResolver.OrderDecisionDefault;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.gui.PhaseHeroesGuiCoordinator;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjectsDefault;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearchersMaster;

import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 * 
 */
public class PhaseHeroesDefault extends PhaseOrderableObjectsDefault implements
		PhaseHeroes {

	private final PhaseHeroesOrdersMaster heroesOrdersMaster;
	private final TilePathSearchersMaster tilePathSearchersMaster;
	private final EnemiesHelper enemiesHelper;
	private final GatherCubesMaster gatherCubesMaster;
	private final TilesMaster tilesMaster;
	private PhaseHeroesGuiCoordinator phaseHeroesGuiCoordinator;

	private RoundDecisionsIterator roundDecisionsIterator;
	private MapsResolver mapsResolver;

	/**
	 * Active path created by picking order valid Tile.
	 */
	private TilePath activePath = null;

	/**
	 * Is any of the phaseObjects objects executing an order?
	 * 
	 * TODO: common variable and mechanism for PhaseOrderableObjects phase?
	 */
	private Boolean orderInProgress = false;

	public PhaseHeroesDefault(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final TilesMaster tilesMaster, final OrdersMaster ordersMaster,
			final TilePathSearchersMaster tilePathSearchersMaster,
			final TilesLookController tilesLookController) {
		super(worldObjectsMastersContainer.getHeroesMaster(), ordersMaster,
				tilesLookController, "PhaseHeroes");

		this.tilePathSearchersMaster = tilePathSearchersMaster;
		this.tilesMaster = tilesMaster;

		enemiesHelper = new EnemiesHelper(
				worldObjectsMastersContainer.getEnemiesMaster());

		heroesOrdersMaster = new PhaseHeroesOrdersMasterDefault(
				tilesLookController, tilesMaster,
				worldObjectsMastersContainer.getHeroesToolsMaster(),
				enemiesHelper);

		this.gatherCubesMaster = worldObjectsMastersContainer
				.getGatherCubesMaster();

		// phaseHeroesGuiCoordinator is set in setRoundDecisionsIterator()
		this.phaseHeroesGuiCoordinator = null;

	}

	@Override
	public void onTilePicked(final Tile tile) {
		if (!orderInProgress) {
			final WorldObject activeObject = currentObject();

			final TilePath pathToTile = tilePathSearchersMaster
					.getTilePathSearcherValidPath().search(activeObject, tile);

			final boolean startOrderValid = startOrderValid(activeObject, tile,
					pathToTile);

			heroesOrdersMaster.tilePicked(tile, startOrderValid);

			if (startOrderValid) {
				activePath = pathToTile;
			} else {
				activePath = null;
			}
		}
	}

	@Override
	public void startPhase() {
		nextHero();
		heroesOrdersMaster
				.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);

		if (mapsResolver.isResolverWorking()) {
			solverIter();
		}
	}

	@Override
	public void onOrderFinished() {

		orderInProgress = false;

		removeDeadObjects();

		if (roundsMaster.getGameResultMaster().isGameWon()
				|| victoryConditionsMet()) {
			roundDecisionsIteratorWin();
			roundsMaster.reload();
			roundsMaster.getGameResultMaster().resetGameResult();
			return;
		}

		if (isCurrentObjectLast()) {
			try {
				phaseIsOver(this);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			return;
		} else {
			// handle next object
			nextHero();
			try {
				heroesOrdersMaster
						.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);
			} catch (final Exception e1) {
				e1.printStackTrace();
			}
		}

		if (mapsResolver.isResolverWorking()) {
			solverIter();
		}

	}

	@Override
	public void onGuiEvent(final GuiElementsContainer eventGui) {
		phaseHeroesGuiCoordinator.onGuiEvent(eventGui);
	}

	@Override
	public void startOrderClicked() {
		if (!orderInProgress) {
			if (activePath != null) {
				orderInProgress = true;
				try {
					heroesOrdersMaster
							.changePhaseHeroesMode(PhaseHeroesMode.MODE_ORDER_EXECUTION);
				} catch (final Exception e) {
					e.printStackTrace();
				}

				ordersMaster.startOrder(currentObject(), activePath, this);
				activePath = null;
			}
		}
	}

	@Override
	public void nextHero() {
		nextObject();
		heroesOrdersMaster.setCurrentHero(currentObject());
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
	public void solverIter() {
		final GameMemento memento = roundsMaster.createMemento();

		// inform current component about the results of the last decision
		roundDecisionsIterator.currentItem().setSnapshotAfterDecision(memento);
		// roundDecisionsIterator.currentItem().makeDecision();
		roundDecisionsIterator.next().makeNextDecision();
	}

	@Override
	public void setRoundDecisionsIterator(
			final RoundDecisionsIterator roundDecisionsIterator,
			final MapsResolver mapsResolver) {
		this.roundDecisionsIterator = roundDecisionsIterator;
		this.mapsResolver = mapsResolver;

		this.phaseHeroesGuiCoordinator = new PhaseHeroesGuiCoordinator(this,
				roundsMaster, mapsResolver, gatherCubesMaster,
				heroesOrdersMaster);
	}

	@Override
	public void resolveDecision(final OrderDecisionDefault orderDecision) {
		final Vector2 decisionPos = orderDecision.getChosenTilePos();
		final Tile chosenTile = tilesMaster.getTilesContainer().getTileOnPos(
				decisionPos);
		onTilePicked(chosenTile);
		startOrderClicked();
	}

	private void roundDecisionsIteratorWin() {
		mapsResolver.victoryConditionsMet();
	}

	/**
	 * Check if given order can be started.
	 * 
	 * @return can given order be started?
	 */
	private Boolean startOrderValid(final WorldObject activeObject,
			final Tile tile, final TilePath pathToTile) {

		if (tile.isOccupied(activeObject)) {
			return true;
		}

		if (pathToTile.length() == 0) {
			return false;
		}

		if (!tile.isTilePathSearchValid()) {
			return false;
		}

		if (!orderInProgress
				&& pathToTile.length() - 1 <= activeObject.getSpeed()) {
			return true;
		}

		return false;
	}
}