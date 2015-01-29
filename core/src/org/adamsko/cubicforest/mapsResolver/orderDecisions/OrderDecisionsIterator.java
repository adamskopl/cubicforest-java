package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.Nullable;

/**
 * Interface for classes iterating through {@link OrderDecisionsAggregate}
 */
public interface OrderDecisionsIterator extends Nullable {

	OrderDecision first();

	OrderDecision next();

	OrderDecision currentItem();

	boolean isDone();

}
