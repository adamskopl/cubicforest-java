package org.adamsko.cubicforest.world.tilePathsMaster.searcher.nearestTile;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;
import org.adamsko.cubicforest.world.tilePathsMaster.NullTilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearcher;

/**
 * Method searching for the path from the source to the tile that is nearest to
 * the target and is reachable by source.<br>
 * Algorithm: for every value from 1 to map's size value, search for tiles that
 * are away this value from target object. Take into account search parameter,
 * exclude unwanted tiles. For every found tile, check if there's a valid path.
 * This is searched path.
 * 
 * @author adamsko
 * 
 */
public class TilePathSearcherNearestTile implements TilePathSearcher {

	private final TilesMaster tilesMaster;
	// used as a stage of searching
	private final TilePathSearcher tilePathSearcherValidTile;

	public TilePathSearcherNearestTile(final TilesMaster tilesMaster,
			final TilePathSearcher tilePathSearcherValidTile) {
		this.tilesMaster = tilesMaster;
		this.tilePathSearcherValidTile = tilePathSearcherValidTile;
	}

	@Override
	public TilePath search(final Tile from, final Tile to) {
		return NullTilePath.instance();
	}

	@Override
	public TilePath search(final WorldObject objectFrom, final Tile destTile) {
		return NullTilePath.instance();
	}

	@Override
	public TilePath search(final WorldObject objectFrom,
			final WorldObject objectTo,
			final TilesSearchParameter tilesSearchParameter) {
		// for distances from 1 to map's size
		for (int distance = 1; distance <= tilesMaster.getMapSize(); distance++) {

			// search for tiles that are 'distance' away from objectTo
			final List<Tile> distanceTiles = tilesMaster.getTilesAway(objectTo,
					distance, tilesSearchParameter);

			// check if objectFrom has a valid path to any of the found tiles
			for (final Tile tile : distanceTiles) {
				final TilePath validPath = tilePathSearcherValidTile.search(
						objectFrom, tile);
				if (validPath.length() > 0) {
					return validPath;
				}
			}

		}
		return NullTilePath.instance();
	}
}
