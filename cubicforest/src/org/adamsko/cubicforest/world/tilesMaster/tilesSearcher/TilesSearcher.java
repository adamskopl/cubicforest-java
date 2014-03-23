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

	public static void setTilesMaster(TilesMaster tilesMaster) {
		TilesSearcher.tilesMaster = tilesMaster;
		adjacentTilesSearcher = new AdjacentTilesSearcher(tilesMaster);
	}

	public static List<Tile> getTilesAdjacent(Tile tile,
			TilesContainer tilesContainer, Boolean getOccupied) {

		List<Tile> adjTiles = new ArrayList<Tile>();
		List<Vector2> adjPositions = new ArrayList<Vector2>();
		adjPositions.add(new Vector2(-1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, 1.0f));
		adjPositions.add(new Vector2(1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, -1.0f));
		for (Vector2 adjPos : adjPositions) {
			adjPos.add(tile.getTilesPos());
			Tile adjTile = tilesContainer.getTileOnPos(adjPos);
			if (adjTile == null) {
				continue;
			}
			if (!getOccupied && adjTile.isOccupied()) {
				continue;
			}
			adjTiles.add(adjTile);
		}
		return adjTiles;
	}

	public static List<Tile> getTilesInRange(Tile tile, int range,
			Boolean getOccupied) {
		return adjacentTilesSearcher.getTilesInRange(tile, range, getOccupied);
	}
}
