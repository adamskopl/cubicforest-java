package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;

/**
 * Generates {@link TilePathDefault} object from (...)
 * 
 * @author adamsko
 * 
 */
public class TilePathSearcherDefault implements TilePathSearcher {

	private TilesMaster tilesMaster = null;
	private TilePathSearcherHelper helper;

	@Override
	public void setTilesMaster(final TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		helper = new TilePathSearcherHelper(tilesMaster);
	}

	@Override
	public TilePath search(final Tile from, final Tile to) {
		if (from == to) {
			// return path with 'from' tile
			return new TilePathDefault(from);
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

	@Override
	public TilePath search(final WorldObject objectOnPath, final Tile destTile) {
		final Tile srcTile = tilesMaster.getTileWithObject(objectOnPath);

		if (srcTile.isNull()) {
			Gdx.app.error("search", "NULL");
		}

		return search(srcTile, destTile);
	}

	@Override
	public TilePath search(final WorldObject objectFrom,
			final WorldObject objectTo) {
		final Tile srcTile = tilesMaster.getTileWithObject(objectFrom);
		final Tile destTile = tilesMaster.getTileWithObject(objectTo);

		final TilePath searchedPath = search(srcTile, destTile);
		return searchedPath;
	}

	@Override
	public TilePath searchShortestPathAdjacentTiles(
			final WorldObject objectFrom, final WorldObject objectTo) {
		final Tile tileTo = tilesMaster.getTileWithObject(objectTo);
		final List<Tile> adjacentTiles = tilesMaster.getTilesAdjacent(tileTo,
				true);

		TilePath shortestPath = null;
		for (final Tile adjacentTile : adjacentTiles) {

			if (adjacentTile.hasOccupant()) {
				continue;
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

}
