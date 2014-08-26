package org.adamsko.cubicforest.world.tile;

public class NullTile extends Tile {

	private static NullTile instance = null;

	public NullTile() {
		super();
	}

	public static NullTile instance() {
		if (instance == null) {
			instance = new NullTile();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
