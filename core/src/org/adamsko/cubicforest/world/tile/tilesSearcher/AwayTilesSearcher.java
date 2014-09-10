package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;

/**
 * Interface for class responsible for tiles search methods associated with
 * tiles being in exact distance of given tile position.
 * 
 * @author adamsko
 * 
 */
public interface AwayTilesSearcher {

	/**
	 * Get tiles, that are away from given distance. <br>
	 * E.g. tiles that are 1 tile away, are the adjacent tiles. Tiles that are 2
	 * tiles away don't include adjacent tiles.
	 * 
	 * @param tile
	 *            object from which distance is measured
	 * @param distance
	 *            how far away searched tiles should be from the given object
	 * @param tilesSearchParameter
	 *            search parameter describing what tiles should be searched
	 * @return list of tiles that are 'distance' value away from given object
	 */
	List<Tile> getTilesAway(final Tile tile, final int distance,
			TilesSearchParameter tilesSearchParameter);

}
