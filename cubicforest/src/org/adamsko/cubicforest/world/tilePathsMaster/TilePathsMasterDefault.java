package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandlerDefault;

import com.badlogic.gdx.Gdx;

/**
 * Stores and manages {@link TilePath} objects for all {@link WorldObject}
 * objects.
 * 
 * @author adamsko
 */
public class TilePathsMasterDefault implements TilePathsMaster {

	private final List<TilePathGuide> tilePathGuides;
	// private final TilesMaster tilesMaster;
	private final TilesEventsHandlerDefault tilesEventsHandler;
	private final OrdersMaster master;

	public TilePathsMasterDefault(final OrdersMaster master,
			final TilesEventsHandlerDefault tilesEventsHandler) {
		if (tilesEventsHandler.isNull()) {
			Gdx.app.error("TilePathsMasterDefault()",
					"tilesEventsHandler.isNull()");
		}
		this.master = master;
		this.tilesEventsHandler = tilesEventsHandler;
		tilePathGuides = new ArrayList<TilePathGuide>();
	}

	public String getName() {
		return "TilePathsMaster";
	}

	@Override
	public void startPath(final WorldObject wanderer, final TilePath path) {
		final TilePathGuide guide = new TilePathGuideDefault();
		tilePathGuides.add(guide);
		guide.start(wanderer, path, this, tilesEventsHandler);
	}

	/**
	 * TODO check if path ended with success or failure. If with failure: try
	 * one more time
	 * 
	 * @param guide
	 */
	@Override
	public void onPathEnd(TilePathGuide guide) {

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
