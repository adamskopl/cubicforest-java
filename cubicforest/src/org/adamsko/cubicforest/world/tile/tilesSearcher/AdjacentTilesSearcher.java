package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

public class AdjacentTilesSearcher {

	private TilesMaster tilesMaster;

	/**
	 * List of ready tiles found during the search.
	 * 
	 * Key: cost of reaching tiles for readyTiles[Key] values.
	 * 
	 * Value: tiles with Key reaching cost.
	 */
	private List<List<Tile>> costTiles;
	// should occupied tiles be considered?
	private Boolean getOccupiedTiles;
	
	/**
	 * range in which adjacent tiles are searched 
	 */
	private int range;
	
	public AdjacentTilesSearcher(TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		costTiles = new ArrayList<List<Tile>>();
	}

	List<Tile> getTilesInRange(Tile tile, int range, Boolean getOccupiedTiles) {

		this.getOccupiedTiles = getOccupiedTiles;
		this.range = range;
		
		costTiles.clear();
		
		// initialize search with source Tile (cost == 0)
		addNextCost();
		addActualCostTile(tile);
		
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
		List<Tile> tilesFound = new ArrayList<Tile>();
		for (List<Tile> cTiles : costTiles) {
			for (Tile costTile : cTiles) {
				tilesFound.add(costTile);
			}
		}
		return tilesFound;
	}
	
	/**
	 * nextCost() recursively performs search operations for actually considered
	 * cost
	 */
	private void handleNextCost() {
		// add vector of tiles for actually considered cost
		addNextCost();
		boolean anyTileAdded = false;
		for (Tile prevTile : previousCostTiles()) {
			List<Tile> adjacentTiles = tilesMaster.getTilesAdjacent(prevTile, true);
			int tilesAdded = addAdjacentTilesCurrentCost(adjacentTiles);
			if (tilesAdded > 0) {
				anyTileAdded = true;
			}
		}
		if (!anyTileAdded) {
			// no tiles added for current cost, no further tiles will be added
			return;
		}
		if(getActualCost() == range) {
			return;
		}
		
		handleNextCost();
	}
	
	private void addNextCost() {
		List<Tile> nextCostTiles = new ArrayList<Tile>();
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
	private int addAdjacentTilesCurrentCost(List<Tile> adjacentTiles) {
		int tilesAdded = 0;
		for (Tile tile : adjacentTiles) {
			if (tileValidCurrentCost(tile)) {
				addActualCostTile(tile);
				tilesAdded++;
			}
		}
		
		return tilesAdded;
	}
	
	/**
	 * Check if given tile can be added to current cost tiles. Conditions:
	 * 
	 * 1) Given tile is not already added to readyTiles (it does not have lower
	 * cost than actually considered one).
	 * 
	 * 2) Tile is valid depend from getOccupiedTiles variable
	 * 
	 * @param tile
	 *            Tile checked
	 * @return decision: tile can be added or not
	 */
	private boolean tileValidCurrentCost(Tile tileChecked) {
		if (!getOccupiedTiles && !tileChecked.isPassable()) {
			return false;
		}
		// check if tileChecked is not already added (does not have lower cost
		// already)
		for (List<Tile> cTiles : costTiles) {
			for (Tile costTile : cTiles) {
				if (tileChecked == costTile) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Add tile to readyTile with actually considered cost.
	 * 
	 * @param readyTile
	 */
	private void addActualCostTile(Tile readyTile) {
		actualCostTiles().add(readyTile);
	}
	
	/**
	 * For actual cost N there are N+1 elements.
	 * 
	 * @return
	 */
	private List<Tile> actualCostTiles() {
		return costTiles.get(costTiles.size() - 1);
	}
	
	/**
	 * For actual cost N there are N+1 elements.
	 * 
	 * @return
	 */
	private List<Tile> previousCostTiles() {
		return costTiles.get(costTiles.size() - 2);
	}
	
	/**
	 * Get actually considered cost.
	 * 
	 * @return actually considered cost
	 */
	private int getActualCost() {
		return costTiles.size() - 1;
	}

}
