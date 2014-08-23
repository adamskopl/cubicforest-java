package org.adamsko.cubicforest.screens;

import org.adamsko.cubicforest.render.world.WorldRenderer;
import org.adamsko.cubicforest.world.World;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen {

	Game game;
	World world;
	WorldRenderer worldRenderer;

	public static TweenManager tweenManager;

	public GameScreen(final Game game) {
		this.game = game;
	}

	private void initTween() {
		tweenManager = new TweenManager();
		Tween.registerAccessor(WorldObject.class, new WorldObjectAccessor());
	}

	@Override
	public void show() {
		Gdx.app.setLogLevel(com.badlogic.gdx.Application.LOG_DEBUG);
		initTween();
		worldRenderer = new WorldRenderer();
		world = new World(worldRenderer);

	}

	@Override
	public void render(float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		world.update(delta);
		tweenManager.update(delta);

		Gdx.gl.glClearColor(0.2f, 0.3f, 0.4f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		worldRenderer.render(delta);
		// controlRenderer.render();
	}

	@Override
	public void hide() {
		worldRenderer.dispose();
		// controlRenderer.dispose();
	}

	@Override
	public void resize(final int width, final int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
