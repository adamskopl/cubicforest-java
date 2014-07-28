package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.debug.GuiDebug;
import org.adamsko.cubicforest.gui.debug.GuiElementDebug;
import org.adamsko.cubicforest.gui.heroTools.GuiElementHeroTool;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.levels.GuiElementLevel;
import org.adamsko.cubicforest.gui.levels.GuiLevels;
import org.adamsko.cubicforest.gui.orders.GuiElementOrder;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.tilePathsSearcher.SearchMethodType;
import org.adamsko.cubicforest.world.tilePathsMaster.tilePathsSearcher.TilePathSearcher;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class PhaseHeroes extends PhaseOrderableObjects {

	private final PhaseHeroesOrdersMaster heroesOrdersMaster;
	private final GatherCubesMaster gatherCubesMaster;

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
			final OrderableObjectsContainer orderableObjectsContainer,
			final OrdersMaster ordersMaster, final TilesMaster tilesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final GatherCubesMaster gatherCubesMaster) {
		super(orderableObjectsContainer, ordersMaster, "PhaseHeroes");

		this.gatherCubesMaster = gatherCubesMaster;
		heroesOrdersMaster = new PhaseHeroesOrdersMaster(tilesMaster,
				heroesToolsMaster);
	}

	@Override
	public void onTileEvent(final Tile tile, final TileEvent event) {

		WorldObject activeObject = null;

		if (!orderInProgress) {
			activeObject = activeObject();
			final TilePath pathToTile = TilePathSearcher.search(activeObject,
					tile, SearchMethodType.METHOD_VALID_PATH);

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

	/**
	 * 
	 */
	private void startOrderClicked() {
		if (!orderInProgress) {
			// if (activePath != null && activePath.length() > 0) {
			if (activePath != null) {
				orderInProgress = true;

				try {
					heroesOrdersMaster
							.changePhaseHeroesMode(PhaseHeroesMode.MODE_ORDER_EXECUTION);
				} catch (final Exception e) {
					e.printStackTrace();
				}

				ordersMaster.unhighlightTilesObjectRange(activeObject());

				activeObject().restoreMovementPoints();
				ordersMaster.startOrder(activeObject(), activePath, this);
				activePath = null;
			}
		}
	}

	/**
	 * Check if given order can be started.
	 * 
	 * @param activeObject
	 * @param pathToTile
	 * 
	 * @return can given order be started?
	 */
	private Boolean startOrderValid(final WorldObject activeObject,
			final Tile tile, final TilePath pathToTile) {

		if (tile.hasOccupant()) {
			// occupied tile with an active object can be chosen
			if (tile.getOccupant() == activeObject) {
				return true;
			}
			return false;
		}

		if (!orderInProgress
				&& pathToTile.length() - 1 <= activeObject.getSpeed()) {
			return true;
		}

		return false;
	}

	@Override
	public void startPhase() {
		nextHero();
	}

	@Override
	public void onOrderFinished(final OrdersMasterResult result,
			final WorldObject objectWithOrder) {

		orderInProgress = false;

		if (roundsMaster.getGameResult() == GameResult.GAME_WON) {
			roundsMaster.reload();
			roundsMaster.resetGameResult();
			return;
		}

		try {
			heroesOrdersMaster
					.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);
		} catch (final Exception e1) {
			e1.printStackTrace();
		}

		if (isActiveObjectLast()) {
			try {
				phaseIsOver(this);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			return;
		} else {
			// handle next object
			nextHero();
		}

	}

	private void nextHero() {
		nextObject();
		ordersMaster.highlightTilesObjectRange(activeObject());
	}

	@Override
	public void onGuiEvent(final GuiContainer eventGui) {
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

		default:
			break;
		}

	}

	private void guiLevelsClicked(final GuiLevels guiLevels) {
		final GuiElementLevel clickedElementLevel = (GuiElementLevel) guiLevels
				.getClickedElement();

		roundsMaster.setMapActive(clickedElementLevel.getLevelIndex());

		ordersMaster.unhighlightTilesObjectRange(activeObject());
		roundsMaster.reload();
	}

	private void guiDebugClicked(final GuiDebug guiDebug) {
		final GuiElementDebug clickedElementDebug = (GuiElementDebug) guiDebug
				.getClickedElement();

		switch (clickedElementDebug.getDebugType()) {
		case DEBUG_RELOAD:
			ordersMaster.unhighlightTilesObjectRange(activeObject());
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
			final HeroToolType heroToolType = clickedElement.getHeroToolType();

			if (gatherCubesMaster.isToolAffordable(heroToolType)) {
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

}