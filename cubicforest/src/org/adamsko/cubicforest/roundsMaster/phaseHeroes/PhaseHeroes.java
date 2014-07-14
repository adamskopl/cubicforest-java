package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.debug.GuiDebug;
import org.adamsko.cubicforest.gui.debug.GuiElementDebug;
import org.adamsko.cubicforest.gui.heroTools.GuiElementHeroTool;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
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
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathSearcher;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.Gdx;

public class PhaseHeroes extends PhaseOrderableObjects {

	private PhaseHeroesOrdersMaster heroesOrdersMaster;
	private GatherCubesMaster gatherCubesMaster;

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

	public PhaseHeroes(OrderableObjectsContainer orderableObjectsContainer,
			OrdersMaster ordersMaster, TilesMaster tilesMaster,
			HeroesToolsMaster heroesToolsMaster,
			GatherCubesMaster gatherCubesMaster) {
		super(orderableObjectsContainer, ordersMaster, "PhaseHeroes");

		this.gatherCubesMaster = gatherCubesMaster;
		heroesOrdersMaster = new PhaseHeroesOrdersMaster(tilesMaster,
				heroesToolsMaster);
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent event) {

		WorldObject activeObject = null;

		// allow 'reload' button to be clicked
		roundsMaster.reloadUnlock();

		if (!orderInProgress) {
			activeObject = activeObject();
			TilePath pathToTile = TilePathSearcher.search(activeObject, tile);

			boolean startOrderValid = startOrderValid(activeObject, tile,
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
				} catch (Exception e) {
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
	private Boolean startOrderValid(WorldObject activeObject, Tile tile,
			TilePath pathToTile) {

		if (tile.hasOccupant()) {
			// occupied tile with an active object can be chosen
			if (tile.getOccupant() == activeObject)
				return true;
			return false;
		}

		if (!orderInProgress
				&& pathToTile.length() - 1 <= activeObject.getSpeed())
			return true;

		return false;
	}

	@Override
	public void phaseIsOver() throws Exception {
		super.phaseIsOver(this);
	}

	@Override
	public void startPhase() {
		nextHero();
	}

	@Override
	public void onOrderFinished(OrdersMasterResult result,
			WorldObject objectWithOrder) {

		orderInProgress = false;
		
		if (roundsMaster.getGameResult() == GameResult.GAME_WON) {
			if (roundsMaster.isReloadAllowed()) {
				roundsMaster.reload();
				roundsMaster.resetGameResult();
				return;
			} else
				Gdx.app.error("onOrderFinished()",
						"roundsMaster.isReloadAllowed()==false");
			
			roundsMaster.resetGameResult();
		}
		
		try {
			heroesOrdersMaster
					.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (isActiveObjectLast()) {
			try {
				phaseIsOver();
			} catch (Exception e) {
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
	public void onGuiEvent(GuiContainer eventGui) {
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

		default:
			break;
		}

	}

	private void guiDebugClicked(GuiDebug guiDebug) {
		GuiElementDebug clickedElementDebug = (GuiElementDebug) guiDebug
				.getClickedElement();

		switch (clickedElementDebug.getDebugType()) {
		case DEBUG_RELOAD:
			if (roundsMaster.isReloadAllowed()) {
				ordersMaster.unhighlightTilesObjectRange(activeObject());
				roundsMaster.reload();
			}
			break;

		default:
			break;
		}
	}

	private void guiHeroToolsClicked(GuiHeroTools guiHeroTools) {
		try {
			GuiElementHeroTool clickedElement = (GuiElementHeroTool) guiHeroTools
					.getClickedElement();
			HeroToolType heroToolType = clickedElement.getHeroToolType();

			if (gatherCubesMaster.isToolAffordable(heroToolType)) {
				// change mode and also set marker's type
				heroesOrdersMaster.changePhaseHeroesMode(
						PhaseHeroesMode.MODE_CHOICE_TOOL, heroToolType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void guiOrdersClicked(GuiOrders guiOrders) {
		GuiElementOrder clickedElement = (GuiElementOrder) guiOrders
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}