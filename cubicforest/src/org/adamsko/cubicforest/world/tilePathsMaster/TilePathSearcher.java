package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;

/**
 * Generates {@link TilePath} object from (...)
 * 
 * @author adamsko
 * 
 */
public class TilePathSearcher {

	private static TilesMaster tilesMaster = null;
	private static TilePathSearcherHelper helper;

	//
	// public String toString() {
	// return "TilePathSearcher";
	// }

	public static void setTilesMaster(final TilesMaster tilesMaster) {
		TilePathSearcher.tilesMaster = tilesMaster;
		helper = new TilePathSearcherHelper(tilesMaster);
	}

	public static TilePath search(final WorldObject objectOnPath,
			final Tile destTile) {
		final Tile srcTile = tilesMaster.getTileWithObject(objectOnPath);

		if (srcTile.isNull()) {
			Gdx.app.error("search", "NULL");
		}

		return search(srcTile, destTile);
	}

	public static TilePath search(final WorldObject objectFrom,
			final WorldObject objectTo) {
		final Tile srcTile = tilesMaster.getTileWithObject(objectFrom);
		final Tile destTile = tilesMaster.getTileWithObject(objectTo);

		final TilePath searchedPath = search(srcTile, destTile);
		return searchedPath;

	}

	/**
	 * Search for the shortest path from one {@link WorldObject} object to the
	 * tile adjacent to other given {@link WorldObject} object.
	 * 
	 * @param objectFrom
	 *            object from which path is leading
	 * @param objectTo
	 *            object which adjacent tiles are the ones to which shortest
	 *            path is searched
	 * @return
	 */
	public static TilePath searchShortestPathAdjacentTiles(
			final WorldObject objectFrom, final WorldObject objectTo) {

		final Tile tileTo = tilesMaster.getTileWithObject(objectTo);
		final List<Tile> adjacentTiles = tilesMaster.getTilesAdjacent(tileTo,
				true);

		TilePath shortestPath = null;
		for (final Tile adjacentTile : adjacentTiles) {

			if (Tile.occupantsRefactor) {
				if (adjacentTile.hasOccupant2()) {
					continue;
				}
			} else {
				if (adjacentTile.hasOccupant()) {
					continue;
				}
			}

			final TilePath adjacentTilePath = search(objectFrom, adjacentTile);
			if (shortestPath == null) {
				shortestPath = adjacentTilePath;
				continue;
			}

			if (adjacentTilePath.length() < shortestPath.length()
					|| shortestPath.length() == 0) {
				shortestPath = adjacentTilePath;
			}
		}
		return shortestPath;
	}

	/**
	 * @param from
	 * @param to
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'from' {@link Tile} object
	 */
	public static TilePath search(final Tile from, final Tile to) {

		if (from == to) {
			// return path with 'from' tile
			return new TilePath(from);
		}

		TilePath path = null;

		helper.searchCostTiles(from, to);

		try {
			path = helper.costTilesToPath();
		} catch (final Exception e) {
			Gdx.app.error("TilePath::search()", e.toString());
		}

		return path;
	}

}
