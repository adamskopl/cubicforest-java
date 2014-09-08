package org.adamsko.cubicforest.world.ordersMaster;

public class NullOrdersMaster extends OrdersMasterDefault {
	private static NullOrdersMaster instance = null;

	private NullOrdersMaster() {
		super();
	}

	public static NullOrdersMaster instance() {
		if (instance == null) {
			instance = new NullOrdersMaster();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
