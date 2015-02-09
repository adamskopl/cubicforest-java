package org.adamsko.cubicforest.players;

import com.badlogic.gdx.Gdx;

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

	@Override
	public void startControl() {
		Gdx.app.error("NullPlayer::startControl()", "");
	}

	@Override
	public void onVictoryConditionsMet() {
		Gdx.app.error("NullPlayer::onVictoryConditionsMet()", "");
	}
}
