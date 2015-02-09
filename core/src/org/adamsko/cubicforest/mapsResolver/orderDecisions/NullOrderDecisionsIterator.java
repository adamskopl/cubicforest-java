package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.players.resolver.NullOrderDecision;

public class NullOrderDecisionsIterator implements OrderDecisionsIterator {

	public NullOrderDecisionsIterator() {
	}

	private static NullOrderDecisionsIterator instance = null;

	public static NullOrderDecisionsIterator instance() {
		if (instance == null) {
			instance = new NullOrderDecisionsIterator();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public OrderDecision first() {
		return NullOrderDecision.instance();
	}

	@Override
	public OrderDecision next() {
		return NullOrderDecision.instance();
	}

	@Override
	public OrderDecision currentItem() {
		return NullOrderDecision.instance();
	}

	@Override
	public boolean isDone() {
		return true;
	}
}
