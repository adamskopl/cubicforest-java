package org.adamsko.cubicforest.screens;

//import org.adamsko.cubicforest.render.OnscreenControlRenderer;
import org.adamsko.cubicforest.render.WorldRenderer;
import org.adamsko.cubicforest.world.World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen extends CubicScreen {
	
	World world;
	WorldRenderer renderer;
	//OnscreenControlRenderer controlRenderer;
	
	public GameScreen (Game game) {
		super(game);
	}
	
	@Override
	public void show () {
		renderer = new WorldRenderer();
		world = new World(renderer);
		
//		controlRenderer = new OnscreenControlRenderer(world);
	}
	
	@Override
	public void render (float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		world.update(delta);
		
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
