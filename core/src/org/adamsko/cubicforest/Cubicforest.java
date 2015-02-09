package org.adamsko.cubicforest;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.screens.GameScreen;

import com.badlogic.gdx.Game;

public class Cubicforest extends Game {

	private ConditionalLog conditionalLog;

	@Override
	public void create() {
		conditionalLog = new ConditionalLog();
		setScreen(new GameScreen(this));
	}

}
