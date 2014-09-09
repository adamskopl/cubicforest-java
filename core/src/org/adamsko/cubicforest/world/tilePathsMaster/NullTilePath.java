package org.adamsko.cubicforest.world.tilePathsMaster;


public class NullTilePath extends TilePathDefault {
	private static NullTilePath instance = null;

	private NullTilePath() {
		super();
	}

	public static NullTilePath instance() {
		if (instance == null) {
			instance = new NullTilePath();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
