package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandler;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

/**
 * Guides {@link WorldObject} through {@link TilePath} by changing object's tile
 * position with {@link Tween}
 * https://code.google.com/p/java-universal-tween-engine/. Class should receive
 * request, start moving object and process collisions. It should inform class
 * which started path, when {@link TilePathGuide} finishes its work.
 * 
 * @author adamsko
 */
public interface TilePathGuide extends TweenCallback {

	/**
	 * Start guidance through the tile path.
	 * 
	 * @param wanderer
	 *            object to be guided through the path
	 * @param path
	 *            guide path
	 * @param master
	 *            {@link TilePathsMaster} which will be informed about finished
	 *            path
	 * @param tilesEventsHandler
	 *            handles collision events during guidance through the path
	 */
	void start(WorldObject wanderer, TilePath path, TilePathsMaster master,
			TilesEventsHandler tilesEventsHandler);

}
