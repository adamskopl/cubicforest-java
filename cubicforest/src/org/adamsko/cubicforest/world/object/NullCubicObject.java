package org.adamsko.cubicforest.world.object;

public class NullCubicObject extends CubicObject {

	private static NullCubicObject instance = null;

	public NullCubicObject() {
		super();
	}

	public static NullCubicObject instance() {
		if (instance == null) {
			instance = new NullCubicObject();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
