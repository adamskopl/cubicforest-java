package org.adamsko.cubicforest.world.tile;

/**
 * Interface for class interested in receiving events about picked tiles.
 * 
 * @author adamsko
 * 
 */
public interface TilePickClient {

	/**
	 * Handle event about picked tile.
	 * 
	 * @param tile
	 *            event tile
	 */
	void onTilePicked(Tile tile);

}
