package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

/**
 * Searches for {@link TilePath} basing on tiles from {@link TilesMaster}.
 * 
 * @author adamsko
 */
public interface TilePathSearcher {

	/**
	 * {@link TilesMaster} is needed to get informations about available tiles.
	 */
	void setTilesMaster(final TilesMaster tilesMaster);

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
	 * Search for path from one object to another
	 * 
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'objectFrom' object
	 */
	TilePath search(final WorldObject objectFrom, final WorldObject objectTo);

	/**
	 * Search for the shortest path from one {@link WorldObject} object to the
	 * tile adjacent to other given {@link WorldObject} object.
	 * 
	 * @param objectFrom
	 *            object from which path is leading
	 * @param objectTo
	 *            object which adjacent tiles are the ones to which shortest
	 *            path is searched
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'objectFrom' object
	 */
	TilePath searchShortestPathAdjacentTiles(final WorldObject objectFrom,
			final WorldObject objectTo);

}