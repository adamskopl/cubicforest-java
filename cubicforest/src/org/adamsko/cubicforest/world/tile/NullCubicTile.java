package org.adamsko.cubicforest.world.tile;

public class NullCubicTile extends CubicTile {
	private static NullCubicTile instance = null;

	private NullCubicTile() {
		super();
	}

	public static NullCubicTile instance() {
		if (instance == null) {
			instance = new NullCubicTile();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
