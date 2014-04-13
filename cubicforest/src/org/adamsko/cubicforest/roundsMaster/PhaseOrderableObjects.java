package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterClient;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.sun.org.apache.xpath.internal.operations.Bool;

public abstract class PhaseOrderableObjects implements OrdersMasterClient,
		RoundPhase {

	private String name;
	private RoundsMaster roundsMaster;
	protected OrdersMaster ordersMaster;

	private List<WorldObject> phaseObjects;

	/**
	 * Active object's position on the list.
	 */
	private int activeObjectPointer = -1;

	protected PhaseOrderableObjects(
			OrderableObjectsContainer orderableObjectsContainer,
			OrdersMaster ordersMaster, String name) {

		this.ordersMaster = ordersMaster;
		this.name = name;
		phaseObjects = new ArrayList<WorldObject>();

		for (WorldObject wo : orderableObjectsContainer.getOrderableObjects()) {
			phaseObjects.add(wo);
		}
	}

	/**
	 * 
	 */
	protected void nextObject() {
		if (phaseObjects.size() == 0)
			try {
				phaseIsOver(this);
			} catch (Exception e) {
				e.printStackTrace();
			}

		activeObjectPointer++;
		// check if previous object was the last one
		if (activeObjectPointer == phaseObjects.size()) {
			activeObjectPointer = 0;
		}
	}

	protected Boolean isActiveObjectLast() {
		if (activeObjectPointer + 1 == phaseObjects.size()) {
			return true;
		}
		return false;
	}

	/**
	 * @return WorldObject pointed by activeObjectPointer.
	 */
	protected WorldObject activeObject() {
		WorldObject activeObject = phaseObjects.get(activeObjectPointer);
		return activeObject;
	}

	public void phaseIsOver(RoundPhase phaseOver) throws Exception {
		roundsMaster.phaseIsOver(phaseOver);
	}

	public void setRoundsMaster(RoundsMaster roundsMaster) {
		this.roundsMaster = roundsMaster;
	}

	public String getName() {
		return name;
	}
}
