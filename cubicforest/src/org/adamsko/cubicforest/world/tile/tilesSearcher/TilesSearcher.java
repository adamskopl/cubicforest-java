package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

/**
 * Searches for specific tiles.
 * 
 * @author adamsko
 * 
 */
public interface TilesSearcher {

	/**
	 * Get tiles adjacent to given tile.
	 * 
	 * @param tile
	 *            tile for which search is performed
	 * 
	 * @return list of tiles adjacent to given tile
	 */
	List<Tile> getTilesAdjacent(final Tile tile);

	/**
	 * Searches for all tiles that are in a range of given starting tile. It
	 * means, that these tiles can be reached from starting tile with a valid
	 * {@link TilePath}.
	 * 
	 * 
	 * @param tile
	 *            initial tile for which range tiles are searched
	 * @param range
	 *            how far search should be performed (tiles that are farther are
	 *            excluded from the search)
	 * @return list with found tiles
	 */
	List<Tile> getTilesInRange(final Tile tile, final int range);

}
