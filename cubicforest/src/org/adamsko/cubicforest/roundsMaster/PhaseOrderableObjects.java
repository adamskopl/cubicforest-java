package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public class PhaseOrderableObjects {

	RoundsMaster roundsMaster;
	List<WorldObject> phaseObjects;
	
	PhaseOrderableObjects(OrderableObjectsContainer orderableObjectsContainer) {		
		phaseObjects = new ArrayList<WorldObject>();
		for(WorldObject wo : orderableObjectsContainer.getOrderableObjects()) {
			phaseObjects.add(wo);
		}
	}
	
	public void phaseIsOver(RoundPhase phaseOver) {
		roundsMaster.phaseIsOver(phaseOver);		
	}

	public void setRoundsMaster(RoundsMaster roundsMaster) {
		this.roundsMaster = roundsMaster;
	}

	
}
