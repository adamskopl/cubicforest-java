package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import java.util.ArrayList;
import java.util.List;

public class OrderDecisionsAggregateDefault implements OrderDecisionsAggregate {

	List<OrderDecision> orderDecisions;

	public OrderDecisionsAggregateDefault() {
		orderDecisions = new ArrayList<OrderDecision>();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public OrderDecisionsIterator createIterator() {
		return new OrderDecisionsIteratorDefault(this);
	}

	@Override
	public int count() {
		return orderDecisions.size();
	}

	@Override
	public void append(final OrderDecision orderDecision) {
		orderDecisions.add(orderDecision);
	}

	@Override
	public OrderDecision get(final int index) {
		return orderDecisions.get(index);
	}

}
