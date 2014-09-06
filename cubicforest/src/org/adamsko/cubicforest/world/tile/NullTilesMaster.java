package org.adamsko.cubicforest.world.tile;

public class NullTilesMaster extends TilesMasterDefault {

	private static NullTilesMaster instance = null;

	public NullTilesMaster() {
		super();
	}

	public static NullTilesMaster instance() {
		if (instance == null) {
			instance = new NullTilesMaster();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
