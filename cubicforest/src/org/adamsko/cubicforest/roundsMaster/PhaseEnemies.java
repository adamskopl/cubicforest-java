package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public class PhaseEnemies extends PhaseOrderableObjects implements RoundPhase {

	PhaseEnemies(OrderableObjectsContainer orderableObjectsContainer) {
		super(orderableObjectsContainer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasPhaseEnded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startPhase() {
		// TODO Auto-generated method stub
		
	}

}
