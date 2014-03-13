package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public class PhaseHeroes extends PhaseOrderableObjects implements RoundPhase {

	PhaseHeroes(OrderableObjectsContainer orderableObjectsContainer) {
		super(orderableObjectsContainer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void phaseIsOver() {
		super.phaseIsOver(this);		
	}

	@Override
	public void startPhase() {
		// TODO Auto-generated method stub
		
	}

}
