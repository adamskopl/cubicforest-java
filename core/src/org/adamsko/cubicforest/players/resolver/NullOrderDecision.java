package org.adamsko.cubicforest.players.resolver;

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
