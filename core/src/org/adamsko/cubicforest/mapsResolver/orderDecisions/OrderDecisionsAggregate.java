package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.Nullable;

/**
 * Interface for aggregate holding {@link OrderDecision}.
 */
public interface OrderDecisionsAggregate extends Nullable {

	OrderDecisionsIterator createIterator();

	OrderDecision get(int index);

	int count();

	void append(OrderDecision orderDecision);

	void debugPrint();

}
