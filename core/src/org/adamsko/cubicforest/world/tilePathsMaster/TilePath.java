package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Manages a queue of {@link Tile} objects indicating path to be walked.
 * 
 * @author adamsko
 */
public interface TilePath extends Nullable {

	/**
	 * Adds {@link Tile} object to the list.
	 * 
	 * @param newTile
	 *            new {@link Tile} added to the end of the list.
	 */
	void pushTile(final Tile newTile);

	/**
	 * Add new {@link Tile} object in the front of the list.
	 * 
	 * @param newTile
	 */
	void addTileFront(final Tile newTile);

	/**
	 * Get first tile from the list.
	 */
	Tile getFrontTile();

	/**
	 * Remove first tile from the list.
	 */
	Tile removeFrontTile();

	/**
	 * Get the amount of tiles in the path.
	 */
	int length();

	/**
	 * Check if path is empty (no tiles).
	 */
	boolean isEmpty();

	/**
	 * Shorten path to given length (remove tiles from the end which exceed the
	 * given number)
	 */
	void shortenPath(final int shortLimit);

}
