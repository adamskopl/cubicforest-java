package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * Stores and manages {@link TilesPath} objects for all {@link WorldObject} objects.
 * 
 * @author adamsko
 */
public class TilePathsMaster {
	
	public TilePathsMaster(TilesMaster tilesMaster) {
		TilePathSearcher.setTilesMaster(tilesMaster);
	}
	
	public void startPath(WorldObject objectOnPath, Tile destinationTile) {
		TilePath testPath = TilePathSearcher
				.search(objectOnPath, destinationTile);
		testPath.addObject(objectOnPath);
	}
	
}
