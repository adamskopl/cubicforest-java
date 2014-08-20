package org.adamsko.cubicforest.world.tilesMaster.tilesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class TilesSearcher {

	private static TilesMaster tilesMaster;
	private static AdjacentTilesSearcher adjacentTilesSearcher;

	public static void setTilesMaster(final TilesMaster tilesMaster) {
		TilesSearcher.tilesMaster = tilesMaster;
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
			if (adjTile == null) {
				continue;
			}

			if (Tile.occupantsRefactor) {
				if (!getOccupied && adjTile.hasOccupant2()) {
					continue;
				}
			} else {
				if (!getOccupied && adjTile.hasOccupant()) {
					continue;
				}
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
