package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;

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
	 * Get tiles that are in the range of the given tile. <br>
	 * See
	 * {@link AdjacentTilesSearcher#getTilesInRange(Tile, int, TilesSearchParameter)}
	 */
	List<Tile> getTilesInRange(final Tile tile, final int range,
			TilesSearchParameter tilesSearchParameter);

	/**
	 * Get tiles, that are away from given distance. <br>
	 * See
	 * {@link AwayTilesSearcher#getTilesAway(Tile, int, TilesSearchParameter)}
	 */
	List<Tile> getTilesAway(final Tile tile, final int distance,
			TilesSearchParameter tilesSearchParameter);

}
