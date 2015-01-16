package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;

public interface RoundDecisionsAggregate {

	/**
	 * Add {@link GameMemento} to resolved mementos list. Added memento should
	 * not be resolved again.
	 * 
	 * @param gameMemento
	 */
	public void addResolvedState(GameMemento gameMemento);

	/**
	 * Check if given memento was not already resolved.
	 * 
	 * @param gameMemento
	 *            memento to check
	 * @return true if memento was resolved earlier
	 */
	public boolean isMementoResolved(GameMemento gameMemento);

	public int getMaxDepth();

}
