package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.objectsMasters.items.portals.Portal;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;

import com.badlogic.gdx.math.Vector2;

public class TilesSearcherDefault implements TilesSearcher {

	private final TilesMaster tilesMaster;
	private final AdjacentTilesSearcher adjacentTilesSearcher;
	private final AwayTilesSearcher awayTilesSearcher;

	public TilesSearcherDefault(final TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		adjacentTilesSearcher = new AdjacentTilesSearcherDefault(tilesMaster);
		awayTilesSearcher = new AwayTilesSearcherDefault(tilesMaster);
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
		// check if given tile has portal to another tile
		if (tile.hasPortal()) {
			final Portal pairPortal = tile.getPortal().getTwinPortal();
			final Tile portalTile = tilesContainer
					.getTileWithObject(pairPortal);
			if (!portalTile.isNull()) {
				adjTiles.add(portalTile);
			}
		}
		return adjTiles;
	}

	@Override
	public List<Tile> getTilesInRange(final Tile tile, final int range,
			final TilesSearchParameter tilesSearchParameter) {
		return adjacentTilesSearcher.getTilesInRange(tile, range,
				tilesSearchParameter);
	}

	@Override
	public List<Tile> getTilesAway(final Tile tile, final int distance,
			final TilesSearchParameter tilesSearchParameter) {
		return awayTilesSearcher.getTilesAway(tile, distance,
				tilesSearchParameter);
	}
}
