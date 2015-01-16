package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.gui.debug.GuiDebug;
import org.adamsko.cubicforest.gui.debug.GuiElementDebug;
import org.adamsko.cubicforest.gui.heroTools.GuiElementHeroTool;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.levels.GuiElementLevel;
import org.adamsko.cubicforest.gui.levels.GuiLevels;
import org.adamsko.cubicforest.gui.orders.GuiElementOrder;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.gui.resolver.GuiElementResolver;
import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.mapsResolver.MapsResolver;
import org.adamsko.cubicforest.mapsResolver.OrderDecisionDefault;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;
import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
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
public class PhaseHeroes extends PhaseOrderableObjects {

	private final PhaseHeroesOrdersMaster heroesOrdersMaster;
	private final GatherCubesMaster gatherCubesMaster;
	private final TilePathSearchersMaster tilePathSearchersMaster;
	private final EnemiesHelper enemiesHelper;
	private final TilesMaster tilesMaster;

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

	public PhaseHeroes(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final TilesMaster tilesMaster, final OrdersMaster ordersMaster,
			final TilePathSearchersMaster tilePathSearchersMaster,
			final TilesLookController tilesLookController) {
		super(worldObjectsMastersContainer.getHeroesMaster(), ordersMaster,
				tilesLookController, "PhaseHeroes");

		this.gatherCubesMaster = worldObjectsMastersContainer
				.getGatherCubesMaster();
		this.tilePathSearchersMaster = tilePathSearchersMaster;
		this.tilesMaster = tilesMaster;

		enemiesHelper = new EnemiesHelper(
				worldObjectsMastersContainer.getEnemiesMaster());

		heroesOrdersMaster = new PhaseHeroesOrdersMasterDefault(
				tilesLookController, tilesMaster,
				worldObjectsMastersContainer.getHeroesToolsMaster(),
				enemiesHelper);

	}

	@Override
	public void onTilePicked(final Tile tile) {

		WorldObject activeObject = null;

		if (!orderInProgress) {
			activeObject = currentObject();

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
			roundDecisionsIteratorIter();
		}

	}

	@Override
	public void onOrderFinished() {

		orderInProgress = false;

		removeDeadObjects();

		if (roundsMaster.getGameResult() == GameResult.GAME_WON
				|| victoryConditionsMet()) {
			roundDecisionsIteratorWin();
			roundsMaster.reload();
			roundsMaster.resetGameResult();
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
			roundDecisionsIteratorIter();
		}

	}

	@Override
	public void onGuiEvent(final GuiElementsContainer eventGui) {
		switch (eventGui.getType()) {
		case GUI_ORDERS:
			guiOrdersClicked((GuiOrders) eventGui);
			break;
		case GUI_HERO_TOOLS:
			guiHeroToolsClicked((GuiHeroTools) eventGui);
			break;
		case GUI_DEBUG:
			guiDebugClicked((GuiDebug) eventGui);
			break;

		case GUI_LEVELS:
			guiLevelsClicked((GuiLevels) eventGui);
			break;

		case GUI_RESOLVER:
			guiResolverClicked((GuiResolver) eventGui);
			break;

		default:
			break;
		}

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

	private void startOrderClicked() {
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

	private void nextHero() {
		nextObject();
		heroesOrdersMaster.setCurrentHero(currentObject());
	}

	private void guiLevelsClicked(final GuiLevels guiLevels) {
		final GuiElementLevel clickedElementLevel = (GuiElementLevel) guiLevels
				.getClickedElement();

		roundsMaster.setMapActive(clickedElementLevel.getLevelIndex());
		roundsMaster.reload();
	}

	private void guiResolverClicked(final GuiResolver eventGui) {
		final GuiElementResolver clickedElementResolver = (GuiElementResolver) eventGui
				.getClickedElement();

		switch (clickedElementResolver.getGuiResolverType()) {
		case RESOLVER_START:
			mapsResolver.startNewResolve();
			roundDecisionsIteratorIter();
			break;

		case RESOLVER_SOLUTION:
			break;

		default:
			break;
		}

	}

	private void guiDebugClicked(final GuiDebug guiDebug) {
		final GuiElementDebug clickedElementDebug = (GuiElementDebug) guiDebug
				.getClickedElement();

		switch (clickedElementDebug.getDebugType()) {
		case DEBUG_RELOAD:
			roundsMaster.reload();
			break;

		default:
			break;
		}
	}

	private void guiHeroToolsClicked(final GuiHeroTools guiHeroTools) {
		try {
			final GuiElementHeroTool clickedElement = (GuiElementHeroTool) guiHeroTools
					.getClickedElement();
			final WorldObjectType heroToolType = clickedElement.getType();

			if (gatherCubesMaster.getGatherCubesCounter().isToolAffordable(
					heroToolType)) {
				// change mode and also set marker's type
				heroesOrdersMaster.changePhaseHeroesMode(
						PhaseHeroesMode.MODE_CHOICE_TOOL, heroToolType);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void guiOrdersClicked(final GuiOrders guiOrders) {
		final GuiElementOrder clickedElement = (GuiElementOrder) guiOrders
				.getClickedElement();

		switch (clickedElement.getOrderType()) {
		case ORDER_ACCEPT:
			startOrderClicked();
			break;
		case ORDER_CANCEL:
			try {
				// cancel tool adding
				heroesOrdersMaster
						.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Check if game is victorious. <br>
	 * Victory conditions: there are no heroes in the heroes phase.
	 */
	private boolean victoryConditionsMet() {
		return noPhaseObjects();
	}

	/**
	 * {@link PhaseHeroesOrdersMaster#getCurrentPossbileDecisions()}
	 */
	public List<OrderDecisionDefault> getCurrentPossbileDecisions() {
		return heroesOrdersMaster.getCurrentPossbileDecisions();
	}

	private void roundDecisionsIteratorWin() {
		mapsResolver.victoryConditionsMet();
	}

	private void roundDecisionsIteratorIter() {
		final GameMemento memento = roundsMaster.createMemento();

		// inform current component about the results of the last decision
		roundDecisionsIterator.currentItem().setSnapshotAfterDecision(memento);
		// roundDecisionsIterator.currentItem().makeDecision();
		roundDecisionsIterator.next().makeNextDecision();
	}

	public void setRoundDecisionsIterator(
			final RoundDecisionsIterator roundDecisionsIterator,
			final MapsResolver mapsResolver) {
		this.roundDecisionsIterator = roundDecisionsIterator;
		this.mapsResolver = mapsResolver;
	}

	public void resolveDecision(final OrderDecisionDefault orderDecision) {
		final Vector2 decisionPos = orderDecision.getChosenTilePos();
		final Tile chosenTile = tilesMaster.getTilesContainer().getTileOnPos(
				decisionPos);
		onTilePicked(chosenTile);
		startOrderClicked();
	}
}