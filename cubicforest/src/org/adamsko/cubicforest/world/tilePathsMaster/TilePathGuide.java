package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.screens.GameScreen;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.WorldObjectAccessor;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Circ;
import aurelienribon.tweenengine.equations.Linear;
import aurelienribon.tweenengine.equations.Quad;
import aurelienribon.tweenengine.equations.Sine;

/**
 * Guides {@link WorldObject} through {@link TilePath} with tweeners.
 * 
 * @author adamsko
 * 
 */
public class TilePathGuide implements TweenCallback {

	/**
	 * Stages of guiding object through the path.
	 * 
	 * @author adamsko
	 * 
	 */
	private enum GuidStage_e {
		/**
		 * Object is heading from previous tile's border, to the next tile's
		 * center.
		 */
		HEADING_CENTER,
		/**
		 * Object is heading from previous tile's center, to the next tile's
		 * border.
		 */
		HEADING_BORDER
	}

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
		Tile t = path.frontTile();
		t = path.frontTile();

		Tween.to(wanderer, WorldObjectAccessor.TILESPOS_X, 0.4f)
				.target(t.getTilesPosX() + 0.5f).ease(Linear.INOUT)
				.setCallback(this).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(GameScreen.tweenManager);
	}

	@Override
	public void onEvent(int type, BaseTween<?> source) {
		Gdx.app.log("onEvent", "tweenComplete");
	}

}
