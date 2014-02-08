package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * Stores and manages {@link TilesPath} objects for all {@link WorldObject}
 * objects.
 * 
 * @author adamsko
 */
public class TilePathsMaster {

	private List<TilePathGuide> tilePathGuides;
	private TilesMaster tilesMaster;

	public TilePathsMaster(TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		TilePathSearcher.setTilesMaster(tilesMaster);
		tilePathGuides = new ArrayList<TilePathGuide>();
	}

	public void startPath(WorldObject wanderer, Tile destinationTile) {
		TilePath testPath = TilePathSearcher.search(wanderer,
				destinationTile);
		TilePathGuide guide = new TilePathGuide(wanderer, testPath, this, tilesMaster);
		tilePathGuides.add(guide);
		guide.start();
	}
	
	protected void onPathEnd(TilePathGuide guide) {
		guide = null;
		// remove guide from TilePathGuide vector
	}

}
