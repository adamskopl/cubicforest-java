package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class TilesSearcherDefault implements TilesSearcher {

	private final TilesMaster tilesMaster;
	private final AdjacentTilesSearcher adjacentTilesSearcher;

	public TilesSearcherDefault(final TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		adjacentTilesSearcher = new AdjacentTilesSearcherDefault(tilesMaster);
	}

	@Override
	public List<Tile> getTilesAdjacent(final Tile tile) {

		final List<Tile> adjTiles = new ArrayList<Tile>();
		final List<Vector2> adjPositions = new ArrayList<Vector2>();
		adjPositions.add(new Vector2(-1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, 1.0f));
		adjPositions.add(new Vector2(1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, -1.0f));

		final TilesContainer tilesContainer = tilesMaster.getTilesContainer();
		for (final Vector2 adjPos : adjPositions) {
			adjPos.add(tile.getTilesPos());
			final Tile adjTile = tilesContainer.getTileOnPos(adjPos);
			if (adjTile.isNull()) {
				continue;
			}

			adjTiles.add(adjTile);
		}
		return adjTiles;
	}

	@Override
	public List<Tile> getTilesInRange(final Tile tile, final int range) {
		return adjacentTilesSearcher.getTilesInRange(tile, range);
	}
}
