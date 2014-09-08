package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.world.object.WorldObject;

/**
 * Manages {@link TilePathGuide} objects: receives requests about
 * {@link WorldObject} that need to be guided through {@link TilePath} with
 * {@link TilePathGuide}.
 * 
 * @author adamsko
 * 
 */
public interface TilePathsMaster {

	/**
	 * Receive request about moving object through the path. Start work of the
	 * {@link TilePathGuide}.
	 * 
	 * @param wanderer
	 *            object to be guided through the path
	 * @param path
	 *            path for the object
	 */
	void startPath(final WorldObject wanderer, final TilePath path);

	/**
	 * Invoked when previously started {@link TilePathGuide} has finished its
	 * work. Class that previously invoked
	 * {@link #startPath(WorldObject, TilePath)} should be informed.
	 * 
	 * @param guide
	 */
	void onPathEnd(TilePathGuide guide);

}
