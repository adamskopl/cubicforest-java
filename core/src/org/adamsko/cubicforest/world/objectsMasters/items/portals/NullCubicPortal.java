package org.adamsko.cubicforest.world.objectsMasters.items.portals;

public class NullCubicPortal extends CubicPortal {
	private static NullCubicPortal instance = null;

	private NullCubicPortal() {
		super();
	}

	public static NullCubicPortal instance() {
		if (instance == null) {
			instance = new NullCubicPortal();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
