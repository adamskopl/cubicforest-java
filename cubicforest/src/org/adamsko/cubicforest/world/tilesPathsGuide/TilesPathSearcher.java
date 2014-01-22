package org.adamsko.cubicforest.world.tilesPathsGuide;

import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * Generates {@link TilesPath} object from (...)
 * 
 * @author adamsko
 *
 */
public class TilesPathSearcher {

	private static TilesMaster tilesMaster = null;
	
	public static void setTilesMaster(TilesMaster tilesMaster) {
		TilesPathSearcher.tilesMaster = tilesMaster;
	}
	
	public static TilesPath search(Tile from, Tile to) {
		
		/*
		 * TODO: generate TilesPath with shortest path algorithm. Take Tile
		 * objects from TilesMaster
		 */
		return null;
	}
	
}
