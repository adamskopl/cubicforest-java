package org.adamsko.cubicforest.world.tile.tilesEvents;


public class NullTilesEventsHandler extends TilesEventsHandlerDefault {
	private static NullTilesEventsHandler instance = null;

	private NullTilesEventsHandler() {
		super();
	}

	public static NullTilesEventsHandler instance() {
		if (instance == null) {
			instance = new NullTilesEventsHandler();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
