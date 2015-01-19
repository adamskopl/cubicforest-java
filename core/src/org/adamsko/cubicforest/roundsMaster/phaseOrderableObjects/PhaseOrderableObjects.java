package org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects;

import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;

public interface PhaseOrderableObjects extends RoundPhase {

	void reloadPhase(
			final OrderableObjectsContainer newOrderableObjectsContainer);

	public void nextObject();

	WorldObject currentObject();

	int currentObjectIndex();

	void setCurrentObjectIndex(final int newIndex);

}
