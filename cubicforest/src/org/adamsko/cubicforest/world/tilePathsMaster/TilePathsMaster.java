package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * Stores and manages {@link TilesPath} objects for all {@link WorldObject} objects.
 * 
 * @author adamsko
 */
public class TilePathsMaster {
	
	/**
	 * list of paths, that were generated and their followings are in progress
	 */
	private List <TilePath> handledPaths;
	
	public TilePathsMaster(TilesMaster tilesMaster) {
		TilePathSearcher.setTilesMaster(tilesMaster);
		handledPaths = new ArrayList<TilePath>();
	}
	
	public void startPath(WorldObject objectOnPath, Tile destinationTile) {
		TilePath testPath = TilePathSearcher
				.search(objectOnPath, destinationTile);
		testPath.addObject(objectOnPath);
	}
	
}
