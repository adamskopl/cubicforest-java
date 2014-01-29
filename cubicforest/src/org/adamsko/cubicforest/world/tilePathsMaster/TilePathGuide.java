package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.screens.GameScreen;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.WorldObjectAccessor;

import com.badlogic.gdx.Gdx;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Circ;
import aurelienribon.tweenengine.equations.Quad;
import aurelienribon.tweenengine.equations.Sine;

/**
 * Guides {@link WorldObject} through {@link TilePath} with tweeners.
 * 
 * @author adamsko
 * 
 */
public class TilePathGuide {

	/**
	 * Object on the path.
	 */
	private WorldObject wanderer = null;
	private TilePath path = null;
	
	public TilePathGuide(WorldObject wanderer, TilePath path) {
		this.wanderer = wanderer;
		this.path = path;
	}
	
	/**
	 * Start guiding object through the tiles.
	 */
	public void start() {
		Tween.to(wanderer, WorldObjectAccessor.TILESPOS_X, 2.0f)
		.target(13.5f)
		.delay(0.5f)
		.ease(Circ.INOUT)
		.repeat(3, 1.0f)
		.start(GameScreen.tweenManager);
	}
	
	
}
