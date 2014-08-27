package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesHelper;
import org.adamsko.cubicforest.world.tile.TilesMaster;

public class TilePathSearcherHelper {

	private final TilesMaster tilesMaster;
	/**
	 * List of ready tiles found during searching.
	 * 
	 * Key: cost of reaching tiles for costTiles[Key] values.
	 * 
	 * Value: tiles with Key reaching cost.
	 */
	private final List<List<Tile>> costTiles;
	Tile source, destiny;

	public TilePathSearcherHelper(final TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		costTiles = new ArrayList<List<Tile>>();
	}

	/**
	 * Search tiles connected with source Tile and assign them cost of reaching
	 * them from source.
	 * 
	 * @param source
	 * @param destiny
	 */
	public void searchCostTiles(final Tile source, final Tile destiny) {
		this.source = source;
		this.destiny = destiny;
		costTiles.clear();

		// initialize search with source Tile (cost == 0)
		addNextCost();
		addCurrentCostTile(source);

		// begin recursive adding of the next costs
		handleNextCost();
	}

	/**
	 * Generate {@link TilePath} path from ready costTiles list.
	 * 
	 * @return {@link TilePath} generated from costTiles list.
	 * @throws Exception
	 */
	public TilePath costTilesToPath() throws Exception {
		final TilePath path = new TilePath();

		// return empty path
		if (!destinyFound()) {
			return path;
		}

		// init path with destiny tile (to start reverse recursive searching)
		addTilePathFront(path, destiny);
		// remove highest cost
		costTiles.remove(costTiles.size() - 1);
		popCostsToSource(path);

		return path;
	}

	/**
	 * Recursive function: add tile from the highest cost that is adjecant to
	 * first tile in given {@link TilePath}. Remove highest source. Repeat until
	 * cost 0.
	 * 
	 * @param path
	 *            TilePath filled with removed Tile.
	 * @throws Exception
	 */
	private void popCostsToSource(final TilePath path) throws Exception {
		final List<Tile> currentCostTiles = currentCostTiles();
		final Tile pathFirstTile = path.getFrontTile();
		boolean tileAdded = false;
		for (final Tile costTile : currentCostTiles) {
			if (TilesHelper.areTilesAdjecant(pathFirstTile, costTile)) {
				addTilePathFront(path, costTile);
				tileAdded = true;
				if (costTile == source) {
					// source added to the front of the path
					return;
				}
				// Tile with highest cost added: remove highest cost
				costTiles.remove(costTiles.size() - 1);
				if (costTiles.size() == 0) {
					throw new Exception("popCostsToSource: cost 0 exceeded");
				}
				break;
			}
		}
		if (!tileAdded) {
			throw new Exception("popCostsToSource: no tile added");
		}
		popCostsToSource(path);
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

			if (tilesAdded == -1) {
				return;
			}
		}
		if (!anyTileAdded) {
			// no tiles added for current cost, no further tiles will be added
			return;
		}

		handleNextCost();
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
				if (tile == destiny) {
					return -1;
				}
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
	 * 2) Given tile is passable or is a destiny tile
	 * 
	 * @param tile
	 *            Tile checked
	 * @return decision: tile can be added or not
	 */
	private boolean tileValidCurrentCost(final Tile tileChecked) {

		// if tile is not passable, don't add it, unless it's a destiny
		if (!tileChecked.isTilePathSearchValid() && tileChecked != destiny) {
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

	private void addNextCost() {
		final List<Tile> nextCostTiles = new ArrayList<Tile>();
		costTiles.add(nextCostTiles);
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
	 * Check if destiny Tile is found for the current cost (for the highest
	 * cost)
	 * 
	 * @return
	 */
	private boolean destinyFound() {
		final List<Tile> highestCostTiles = currentCostTiles();
		if (highestCostTiles.contains(destiny)) {
			return true;
		}
		return false;
	}

	/**
	 * @param tile
	 */
	private void addTilePathFront(final TilePath path, final Tile tile) {
		path.addTileFront(tile);
	}

}
