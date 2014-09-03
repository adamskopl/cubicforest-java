package org.adamsko.cubicforest.roundsMaster;


public class NullRoundsMaster extends RoundsMasterDefault {

	private static NullRoundsMaster instance = null;

	private NullRoundsMaster() {
	}

	public static NullRoundsMaster instance() {
		if (instance == null) {
			instance = new NullRoundsMaster();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
