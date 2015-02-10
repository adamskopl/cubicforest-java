package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsAggregate;

/**
 * Interface for a class sharing {@link RoundDecisionsAggregate} objects.
 */
public interface OrderDecisionsAggregateContainer {

	public int countAggregates();

	/**
	 * Get aggregate with given index.
	 * 
	 * @param index
	 *            index of the aggregate
	 * @param prizesCollected
	 *            true if aggregate should be taken from the list defining order
	 *            decisions collecting all prizes on the map
	 */
	OrderDecisionsAggregate getAggregate(int index, boolean prizesCollected);

}
