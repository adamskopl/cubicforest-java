package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.players.resolver.NullOrderDecision;

public class OrderDecisionsIteratorDefault implements OrderDecisionsIterator {

	private final OrderDecisionsAggregate aggregate;
	private int index;

	public OrderDecisionsIteratorDefault(final OrderDecisionsAggregate aggregate) {
		this.aggregate = aggregate;
		this.index = 0;
	}

	@Override
	public OrderDecision first() {
		return aggregate.get(0);
	}

	@Override
	public OrderDecision next() {
		if (index + 1 == aggregate.count()) {
			return NullOrderDecision.instance();
		}
		index++;
		return aggregate.get(index);
	}

	@Override
	public OrderDecision currentItem() {
		return aggregate.get(index);
	}

	@Override
	public boolean isDone() {
		return index + 1 > aggregate.count();
	}

}
