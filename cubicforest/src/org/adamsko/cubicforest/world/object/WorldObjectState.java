package org.adamsko.cubicforest.world.object;

public enum WorldObjectState {

	/**
	 * Object is in play.
	 */
	ALIVE,
	/**
	 * When object is dead, it should be removed from its container and other
	 * container classes like PhaseOrderableObjects.
	 */
	DEAD
}
