package org.adamsko.cubicforest.players.resolver;

import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisions;

public class NullDecisionsComponent extends RoundDecisions {
	private static NullDecisionsComponent instance = null;

	private NullDecisionsComponent() {
		super(false);
	}

	public static NullDecisionsComponent instance() {
		if (instance == null) {
			instance = new NullDecisionsComponent();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
