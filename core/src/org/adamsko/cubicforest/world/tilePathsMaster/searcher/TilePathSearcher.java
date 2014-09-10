package org.adamsko.cubicforest.world.tilePathsMaster.searcher;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

/**
 * Searches for {@link TilePath} basing on tiles from {@link TilesMaster}.
 * 
 * @author adamsko
 */
public interface TilePathSearcher {

	/**
	 * Search for path from one tile to another
	 * 
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'from' {@link Tile} object
	 */
	TilePath search(final Tile from, final Tile to);

	/**
	 * Search for path from an object to a tile
	 * 
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'objectFrom' object
	 */
	TilePath search(final WorldObject objectFrom, final Tile destTile);

	/**
	 * Search for path from one object to another.
	 * 
	 * @param tilesSearchParameter
	 *            search parameter describing which tiles are searched
	 * 
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'objectFrom' object
	 */
	TilePath search(final WorldObject objectFrom, final WorldObject objectTo,
			TilesSearchParameter tilesSearchParameter);

}
