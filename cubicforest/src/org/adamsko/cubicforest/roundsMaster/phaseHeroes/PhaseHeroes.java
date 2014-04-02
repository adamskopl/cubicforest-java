package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.gui.Gui;
import org.adamsko.cubicforest.roundsMaster.PhaseOrderableObjects;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult_e;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathSearcher;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class PhaseHeroes extends PhaseOrderableObjects implements RoundPhase {

	/**
	 * Is any of the phaseObjects objects executing an order?
	 * 
	 * TODO: common variable and mechanism for PhaseOrderableObjects phase?
	 */
	private Boolean orderInProgress = false;

	public PhaseHeroes(OrderableObjectsContainer orderableObjectsContainer,
			OrdersMaster ordersMaster) {
		super(orderableObjectsContainer, ordersMaster, "PhaseHeroes");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {

		WorldObject activeObject = null;
		if (!orderInProgress) {
			activeObject = activeObject();
			// check if tile event is valid
			TilePath pathToTile = TilePathSearcher.search(activeObject, tile);

			// consider path which length is shorter than object's range
			if (pathToTile.length()-1 <= activeObject.getSpeed()) {
				orderInProgress = true;
				Gdx.app.debug(getName(), "startOrder");
				ordersMaster.unhighlightTilesObjectRange(activeObject());
				ordersMaster.startOrder(activeObject, pathToTile, this);
			}
		}
	}

	@Override
	public void phaseIsOver() throws Exception {
		super.phaseIsOver(this);
	}

	@Override
	public void startPhase() {
		Gdx.app.debug(getName(), "startPhase()");
		nextHero();

	}

	@Override
	public void onOrderFinished(OrdersMasterResult_e result,
			WorldObject objectWithOrder) {

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
	public void onGuiEvent(Gui eventGui) {
		Gdx.app.error("onGuiEvent", "PHASE HEROES");		
	}

}
