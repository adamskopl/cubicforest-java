package org.adamsko.cubicforest.world.tile;

import org.adamsko.cubicforest.Nullable;

/**
 * Interface for class interested in receiving events about picked tiles.
 * 
 * @author adamsko
 * 
 */
public interface TilePickClient extends Nullable {

	/**
	 * Handle event about picked tile.
	 * 
	 * @param tile
	 *            event tile
	 */
	void onTilePicked(Tile tile);

}
