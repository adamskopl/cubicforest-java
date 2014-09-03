package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;

/**
 * Stores and manages {@link TilesPath} objects for all {@link WorldObject}
 * objects.
 * 
 * @author adamsko
 */
public class TilePathsMaster {

	private final List<TilePathGuide> tilePathGuides;
	private final TilesMaster tilesMaster;
	private final OrdersMaster master;

	public TilePathsMaster(final OrdersMaster master,
			final TilesMaster tilesMaster) {
		this.master = master;
		this.tilesMaster = tilesMaster;
		tilePathGuides = new ArrayList<TilePathGuide>();
	}

	public String getName() {
		return "TilePathsMaster";
	}

	public void startPath(final WorldObject wanderer, final TilePath path) {
		final TilePathGuide guide = new TilePathGuide(wanderer, path, this,
				tilesMaster);
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

		master.onPathFinished();

		// remove guide from TilePathGuide vector
		try {
			tilePathGuides.remove(guide);
		} catch (final Exception e) {
			Gdx.app.error("TilePathsMaster::onPathEnd", e.toString());
		}

		// uncoment for tiles labels removal
		// tilesMaster.clearTilesLabels();

		// guide is no longer needed
		guide = null;
	}

}
