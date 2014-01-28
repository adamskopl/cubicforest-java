package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.List;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * Generates {@link TilePath} object from (...)
 * 
 * @author adamsko
 *
 */
public class TilePathSearcher {

	private static TilesMaster tilesMaster = null;
	
	public static void setTilesMaster(TilesMaster tilesMaster) {
		TilePathSearcher.tilesMaster = tilesMaster;
	}
	
	public static TilePath search(WorldObject objectOnPath, Tile destTile) {
		Tile srcTile = tilesMaster.getTilesContainer().getTileWithObject(
				objectOnPath);
		return search(srcTile, destTile);
	}
	
	/**
	 * @param from
	 * @param to
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'from' {@link Tile} object
	 */
	public static TilePath search(Tile from, Tile to) {
		
		List<Tile> testTiles = tilesMaster.getPathTestTiles();
		TilePath testPath = new TilePath();
		for(Tile t : testTiles) {
			testPath.pushTile(t);
		}
		
		/*
		 * TODO: generate TilesPath with shortest path algorithm. Take Tile
		 * objects from TilesMaster
		 */
		return testPath;
	}
	
}
