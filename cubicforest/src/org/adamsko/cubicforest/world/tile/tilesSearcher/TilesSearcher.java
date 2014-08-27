package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class TilesSearcher {

	private static AdjacentTilesSearcher adjacentTilesSearcher;

	public static void setTilesMaster(final TilesMaster tilesMaster) {
		adjacentTilesSearcher = new AdjacentTilesSearcher(tilesMaster);
	}

	public static List<Tile> getTilesAdjacent(final Tile tile,
			final TilesContainer tilesContainer, final Boolean getOccupied) {

		final List<Tile> adjTiles = new ArrayList<Tile>();
		final List<Vector2> adjPositions = new ArrayList<Vector2>();
		adjPositions.add(new Vector2(-1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, 1.0f));
		adjPositions.add(new Vector2(1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, -1.0f));
		for (final Vector2 adjPos : adjPositions) {
			adjPos.add(tile.getTilesPos());
			final Tile adjTile = tilesContainer.getTileOnPos(adjPos);
			if (adjTile.isNull()) {
				continue;
			}

			if (!getOccupied && adjTile.isTilePathSearchValid()) {
				continue;
			}

			adjTiles.add(adjTile);
		}
		return adjTiles;
	}

	public static List<Tile> getTilesInRange(final Tile tile, final int range,
			final Boolean getOccupied) {
		return adjacentTilesSearcher.getTilesInRange(tile, range, getOccupied);
	}
}
