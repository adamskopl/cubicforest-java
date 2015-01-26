package org.adamsko.cubicforest.mapsResolver.orderDecisions;

/**
 * Interface for classes iterating through {@link OrderDecisionsAggregate}
 */
public interface OrderDecisionsIterator {

	OrderDecision first();

	OrderDecision next();

	OrderDecision currentItem();

	boolean isDone();

}
