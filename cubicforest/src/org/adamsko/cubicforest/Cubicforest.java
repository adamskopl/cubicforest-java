package org.adamsko.cubicforest;

import org.adamsko.cubicforest.screens.GameScreen;

import com.badlogic.gdx.Game;

public class Cubicforest extends Game  {

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}
	
}
