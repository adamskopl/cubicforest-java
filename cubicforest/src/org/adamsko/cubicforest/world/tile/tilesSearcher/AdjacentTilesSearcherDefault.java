package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

public class AdjacentTilesSearcherDefault implements AdjacentTilesSearcher {

	private final TilesMaster tilesMaster;

	/**
	 * List of ready tiles found during the search.
	 * 
	 * Key: cost of reaching tiles for readyTiles[Key] values.
	 * 
	 * Value: tiles with Key reaching cost.
	 */
	private final List<List<Tile>> costTiles;
	// should occupied tiles be considered?
	private Boolean getOccupiedTiles;

	/**
	 * range in which adjacent tiles are searched
	 */
	private int range;

	public AdjacentTilesSearcherDefault(final TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		costTiles = new ArrayList<List<Tile>>();
	}

	@Override
	public List<Tile> getTilesInRange(final Tile tile, final int range,
			final Boolean getOccupiedTiles) {

		this.getOccupiedTiles = getOccupiedTiles;
		this.range = range;

		costTiles.clear();

		// initialize search with source Tile (cost == 0)
		addNextCost();
		addCurrentCostTile(tile);

		// begin recursive adding of the next costs
		handleNextCost();

		return costTilesToList();
	}

	/**
	 * Convert @costTiles to a list of tiles
	 * 
	 * @return list of all tiles from @costTiles
	 */
	private List<Tile> costTilesToList() {
		final List<Tile> tilesFound = new ArrayList<Tile>();
		for (final List<Tile> cTiles : costTiles) {
			for (final Tile costTile : cTiles) {
				tilesFound.add(costTile);
			}
		}
		return tilesFound;
	}

	/**
	 * nextCost() recursively performs search operations for currently
	 * considered cost
	 */
	private void handleNextCost() {
		// add vector of tiles for currently considered cost
		addNextCost();
		boolean anyTileAdded = false;
		for (final Tile prevTile : previousCostTiles()) {
			final List<Tile> adjacentTiles = tilesMaster.getTilesAdjacent(
					prevTile, true);
			final int tilesAdded = addAdjacentTilesCurrentCost(adjacentTiles);
			if (tilesAdded > 0) {
				anyTileAdded = true;
			}
		}
		if (!anyTileAdded) {
			// no tiles added for current cost, no further tiles will be added
			return;
		}
		if (getCurrentCost() == range) {
			return;
		}

		handleNextCost();
	}

	private void addNextCost() {
		final List<Tile> nextCostTiles = new ArrayList<Tile>();
		costTiles.add(nextCostTiles);
	}

	/**
	 * Consider adding adjacent tiles (tiles adjacent to one of the tiles from
	 * previous cost) to current cost tiles list.
	 * 
	 * @param adjacentTiles
	 * @return Number of added tiles to currently considered cost. -1 if destiny
	 *         tile found.
	 */
	private int addAdjacentTilesCurrentCost(final List<Tile> adjacentTiles) {
		int tilesAdded = 0;
		for (final Tile tile : adjacentTiles) {
			if (tileValidCurrentCost(tile)) {
				addCurrentCostTile(tile);
				tilesAdded++;
			}
		}

		return tilesAdded;
	}

	/**
	 * Check if given tile can be added to current cost tiles. Conditions:
	 * 
	 * 1) Given tile is not already added to readyTiles (it does not have lower
	 * cost than currently considered one).
	 * 
	 * 2) Tile is valid depend from getOccupiedTiles variable
	 * 
	 * @param tile
	 *            Tile checked
	 * @return decision: tile can be added or not
	 */
	private boolean tileValidCurrentCost(final Tile tileChecked) {
		if (!getOccupiedTiles && !tileChecked.isTilePathSearchValid()) {
			return false;
		}
		// check if tileChecked is not already added (does not have lower cost
		// already)
		for (final List<Tile> cTiles : costTiles) {
			for (final Tile costTile : cTiles) {
				if (tileChecked == costTile) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Add tile to readyTile with currently considered cost.
	 * 
	 * @param readyTile
	 */
	private void addCurrentCostTile(final Tile readyTile) {
		currentCostTiles().add(readyTile);
	}

	/**
	 * For current cost N there are N+1 elements.
	 * 
	 * @return
	 */
	private List<Tile> currentCostTiles() {
		return costTiles.get(costTiles.size() - 1);
	}

	/**
	 * For current cost N there are N+1 elements.
	 * 
	 * @return
	 */
	private List<Tile> previousCostTiles() {
		return costTiles.get(costTiles.size() - 2);
	}

	/**
	 * Get currently considered cost.
	 * 
	 * @return currently considered cost
	 */
	private int getCurrentCost() {
		return costTiles.size() - 1;
	}

}
