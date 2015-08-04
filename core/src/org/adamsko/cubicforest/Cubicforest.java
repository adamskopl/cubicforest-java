package org.adamsko.cubicforest;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.screens.GameScreen;

import com.badlogic.gdx.Game;

public class Cubicforest extends Game {

	private CLog conditionalLog;

	@Override
	public void create() {
		conditionalLog = new CLog();
		setScreen(new GameScreen(this));
	}

}
