package org.adamsko.cubicforest.players.resolver;

import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;

public class NullOrderDecision extends OrderDecisionDefault {
	private static NullOrderDecision instance = null;

	private NullOrderDecision() {
		super(false);
	}

	public static NullOrderDecision instance() {
		if (instance == null) {
			instance = new NullOrderDecision();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
