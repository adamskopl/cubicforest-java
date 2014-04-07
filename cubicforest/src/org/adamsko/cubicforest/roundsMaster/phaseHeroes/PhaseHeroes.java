package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.heroTools.GuiElementHeroTool;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.orders.GuiElementOrder;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.roundsMaster.PhaseOrderableObjects;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult_e;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathSearcher;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class PhaseHeroes extends PhaseOrderableObjects implements RoundPhase {

	private PhaseHeroesOrdersMaster heroesOrdersMaster;
	private TilesMaster tilesMaster;

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
			HeroesToolsMaster heroesToolsMaster) {
		super(orderableObjectsContainer, ordersMaster, "PhaseHeroes");

		this.tilesMaster = tilesMaster;
		heroesOrdersMaster = new PhaseHeroesOrdersMaster(tilesMaster,
				heroesToolsMaster);
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {

		WorldObject activeObject = null;

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
			if (activePath != null && activePath.length() > 0) {
				orderInProgress = true;
				try {
					heroesOrdersMaster
							.changePhaseHeroesMode(PhaseHeroesMode_e.MODE_ORDER_EXECUTION);
				} catch (Exception e) {
					e.printStackTrace();
				}

				ordersMaster.unhighlightTilesObjectRange(activeObject());
				ordersMaster.startOrder(activeObject(), activePath, this);
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
		if (!orderInProgress && !tile.hasOccupant()
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
	public void onOrderFinished(OrdersMasterResult_e result,
			WorldObject objectWithOrder) {

		try {
			heroesOrdersMaster
					.changePhaseHeroesMode(PhaseHeroesMode_e.MODE_CHOICE_MOVEMENT);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (activeObject() != objectWithOrder) {
			Gdx.app.error(getName(), "activeObject() != objectWithOrder");
		}

		orderInProgress = false;
		if (isActiveObjectLast()) {
			try {
				phaseIsOver();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		} else {
			// set next object
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

		default:
			break;
		}

	}

	private void guiHeroToolsClicked(GuiHeroTools guiHeroTools) {
		try {
			GuiElementHeroTool clickedElement = (GuiElementHeroTool) guiHeroTools
					.getClickedElement();
			HeroToolType_e heroToolType = clickedElement.getHeroToolType();

			// change mode and also set marker's type
			heroesOrdersMaster.changePhaseHeroesMode(
					PhaseHeroesMode_e.MODE_CHOICE_TOOL, heroToolType);

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
						.changePhaseHeroesMode(PhaseHeroesMode_e.MODE_CHOICE_MOVEMENT);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}