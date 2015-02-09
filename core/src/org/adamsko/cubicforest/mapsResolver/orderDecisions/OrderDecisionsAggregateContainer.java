package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsAggregate;

/**
 * Interface for a class sharing {@link RoundDecisionsAggregate} objects.
 */
public interface OrderDecisionsAggregateContainer {

	public int countAggregates();

	OrderDecisionsAggregate getAggregate(int index);

}
