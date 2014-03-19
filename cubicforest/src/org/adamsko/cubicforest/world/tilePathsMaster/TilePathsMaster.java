package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;

/**
 * Stores and manages {@link TilesPath} objects for all {@link WorldObject}
 * objects.
 * 
 * @author adamsko
 */
public class TilePathsMaster {

	private List<TilePathGuide> tilePathGuides;
	private TilesMaster tilesMaster;
	private OrdersMaster master;

	public TilePathsMaster(OrdersMaster master, TilesMaster tilesMaster) {
		this.master = master;
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
	
	/**
	 * TODO check if path ended with success or failure. If with failure: try
	 * one more time
	 * 
	 * @param guide
	 */
	protected void onPathEnd(TilePathGuide guide) {
		WorldObject wanderer = guide.getWanderer();
		master.onPathFinished(OrdersMasterResult_e.ORDER_PATH_FINISHED, wanderer);

		Gdx.app.debug("TilePathsMaster", "size: "+tilePathGuides.size());
		
		// remove guide from TilePathGuide vector		
		try {
			tilePathGuides.remove(guide);
		} catch (Exception e) {
			Gdx.app.debug("TilePathsMaster::onPathEnd", e.toString());
		}

		tilesMaster.clearTilesLabels();
		// guide is no longer needed
		guide = null;
	}

}
