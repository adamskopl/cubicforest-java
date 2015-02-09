package org.adamsko.cubicforest.roundsMaster;


public class NullPlayerActionsHandlerPhase extends PlayerActionsHandlerPhase {
	private static NullPlayerActionsHandlerPhase instance = null;

	private NullPlayerActionsHandlerPhase() {
		super();
	}

	public static NullPlayerActionsHandlerPhase instance() {
		if (instance == null) {
			instance = new NullPlayerActionsHandlerPhase();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
