package org.adamsko.cubicforest.players;

public class NullPlayer extends PlayerBase {

	private static NullPlayer instance = null;

	private NullPlayer() {
		super(NullPlayersController.instance());
	}

	public static NullPlayer instance() {
		if (instance == null) {
			instance = new NullPlayer();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
