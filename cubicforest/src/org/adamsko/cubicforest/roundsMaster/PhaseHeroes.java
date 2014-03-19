package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult_e;
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
		super(orderableObjectsContainer, ordersMaster);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		Gdx.app.debug("phaseHeroes", "onTileEvent");
		WorldObject activeObject = null;
		if (!orderInProgress) {			
			orderInProgress = true;
			// set next object
			nextObject();
			activeObject = activeObject();
		}
		if(activeObject != null) {
			ordersMaster.startOrder(activeObject, tile, this);
			orderInProgress = true;
		}
	}

	@Override
	public void phaseIsOver() throws Exception {
		super.phaseIsOver(this);
	}

	@Override
	public void startPhase() {
		Gdx.app.debug("PhaseHeroes", "startPhase()");

	}

	@Override
	public void onOrderFinished(OrdersMasterResult_e result,
			WorldObject objectWithOrder) {
		Gdx.app.debug("phaseHeroes", "order finished");
		orderInProgress = false;		
		if(isActiveObjectLast()) {
			try {
				phaseIsOver();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
	}
}
