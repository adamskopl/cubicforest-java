package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.players.resolver.NullOrderDecision;

public class NullOrderDecisionsAggregate implements OrderDecisionsAggregate {

	private static NullOrderDecisionsAggregate instance = null;

	private NullOrderDecisionsAggregate() {
	}

	public static NullOrderDecisionsAggregate instance() {
		if (instance == null) {
			instance = new NullOrderDecisionsAggregate();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public OrderDecisionsIterator createIterator() {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public void append(final OrderDecision orderDecision) {
	}

	@Override
	public OrderDecision get(final int index) {
		return NullOrderDecision.instance();
	}

	@Override
	public void debugPrint() {
	}

}
