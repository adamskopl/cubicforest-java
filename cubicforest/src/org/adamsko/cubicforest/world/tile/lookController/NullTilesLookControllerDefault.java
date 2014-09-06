package org.adamsko.cubicforest.world.tile.lookController;


public class NullTilesLookControllerDefault extends TilesLookControllerDefault {
	private static NullTilesLookControllerDefault instance = null;

	private NullTilesLookControllerDefault() {
		super();
	}

	public static NullTilesLookControllerDefault instance() {
		if (instance == null) {
			instance = new NullTilesLookControllerDefault();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
