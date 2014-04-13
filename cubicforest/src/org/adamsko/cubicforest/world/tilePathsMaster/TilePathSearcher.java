package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

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

	public static void setTilesMaster(TilesMaster tilesMaster) {
		TilePathSearcher.tilesMaster = tilesMaster;
		helper = new TilePathSearcherHelper(tilesMaster);
	}

	public static TilePath search(WorldObject objectOnPath, Tile destTile) {
		Tile srcTile = tilesMaster.getTileWithObject(objectOnPath);

		if(srcTile == null) {
			Gdx.app.error("search", "NULL");
		}
		
		return search(srcTile, destTile);
	}

	public static TilePath search(WorldObject objectFrom, WorldObject objectTo) {
		Tile srcTile = tilesMaster.getTileWithObject(objectFrom);
		Tile destTile = tilesMaster.getTileWithObject(objectTo);

		TilePath searchedPath = search(srcTile, destTile);
		Gdx.app.debug("searchedPath", Integer.toString(searchedPath.length()));
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
			WorldObject objectFrom, WorldObject objectTo) {

//		Gdx.app.debug("searchShortestPathAdjacentTiles", "<<<<<<<<<<<<<");

		Tile tileTo = tilesMaster.getTileWithObject(objectTo);
		List<Tile> adjacentTiles = tilesMaster.getTilesAdjacent(tileTo, true);

		// Gdx.app.debug("adjTiles num: ",
		// Integer.toString(adjacentTiles.size()));

		TilePath shortestPath = null;
		int index = -1;
		for (Tile adjacentTile : adjacentTiles) {
			index++;
			// Gdx.app.debug("adjTile ", Integer.toString(index));

			if (adjacentTile.hasOccupant()) {
//				Gdx.app.debug("adjTilehasOcc", "");
				continue;
			}

			TilePath adjacentTilePath = search(objectFrom, adjacentTile);
			if (shortestPath == null) {
//				Gdx.app.debug(Integer.toString(index) + " adjPath len ",
//						Integer.toString(adjacentTilePath.length()));
				shortestPath = adjacentTilePath;
				continue;
			}

//			Gdx.app.debug(Integer.toString(index) + " adjPath len ",
//					Integer.toString(adjacentTilePath.length()));

			if (adjacentTilePath.length() < shortestPath.length()
					|| shortestPath.length() == 0) {
				shortestPath = adjacentTilePath;
			}
		}
//		Gdx.app.debug("searchShortestPathAdjacentTiles", ">>>>>>>>>>>>>");
		return shortestPath;
	}

	/**
	 * @param from
	 * @param to
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'from' {@link Tile} object
	 */
	public static TilePath search(Tile from, Tile to) {
		if (from == to) {
			return new TilePath();
		}

		TilePath path = null;

		helper.searchCostTiles(from, to);
		try {
			path = helper.costTilesToPath();
		} catch (Exception e) {
			Gdx.app.error("TilePath::search()", e.toString());
		}

		return path;
	}

}
