package org.adamsko.cubicforest.screens;

import org.adamsko.cubicforest.TestClass;
import org.adamsko.cubicforest.render.WorldRenderer;
import org.adamsko.cubicforest.world.World;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.WorldObjectAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen extends CubicScreen {
	
	World world;
	WorldRenderer renderer;
	public static TweenManager tweenManager;
	
	public GameScreen (Game game) {
		super(game);
	}
	
	private void initTween() {
		tweenManager = new TweenManager();
		Tween.registerAccessor(WorldObject.class, new WorldObjectAccessor());		
	}
	
	@Override
	public void show () {
		Gdx.app.setLogLevel(com.badlogic.gdx.Application.LOG_DEBUG);
		
		Boolean performTest = false;
		if(performTest){
			TestClass testClass = new TestClass();
			Gdx.app.exit();
		}
		initTween();
		renderer = new WorldRenderer();
		world = new World(renderer);

	}
	
	@Override
	public void render (float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		world.update(delta);
		tweenManager.update(delta);
		
		Gdx.gl.glClearColor(0.2f, 0.3f, 0.4f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render(delta);
//		controlRenderer.render();
	}
	
	@Override
	public void hide () {
		renderer.dispose();
//		controlRenderer.dispose();
	}

}
