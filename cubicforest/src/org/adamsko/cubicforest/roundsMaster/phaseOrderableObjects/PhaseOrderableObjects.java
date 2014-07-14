package org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterClient;

import com.badlogic.gdx.Gdx;

public abstract class PhaseOrderableObjects implements OrdersMasterClient,
		RoundPhase {

	private String name;
	protected RoundsMaster roundsMaster;
	protected OrdersMaster ordersMaster;

	private List<WorldObject> phaseObjects;
	private OrderableObjectsContainer objectsContainer;
	protected OrdersMasterResultResolver ordersMasterResultResolver;

	/**
	 * Active object's position on the list.
	 */
	private int activeObjectPointer;

	protected PhaseOrderableObjects(
			OrderableObjectsContainer orderableObjectsContainer,
			OrdersMaster ordersMaster,
			OrdersMasterResultResolver ordersMasterResultResolver, String name) {

		this.objectsContainer = orderableObjectsContainer;
		this.ordersMaster = ordersMaster;
		this.name = name;
		this.ordersMasterResultResolver = ordersMasterResultResolver;

		phaseObjects = new ArrayList<WorldObject>();

		reloadPhase();
	}

	/**
	 * 
	 */
	void addObject(WorldObject object) {
		
	}
	
	/**
	 * 
	 */
	void removeObject(WorldObject object) {
		activeObjectPointer--;
		phaseObjects.remove(object);
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
		if(phaseObjects.size() == 0) return null;
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

	@Override
	public void reloadPhase() {
		activeObjectPointer = -1;

		phaseObjects.clear();
		for (WorldObject wo : objectsContainer.getOrderableObjects()) {
			phaseObjects.add(wo);
		}
	}
}