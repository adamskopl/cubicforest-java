package org.adamsko.cubicforest.world.object;


public class NullWorldObject extends WorldObject {

	private static NullWorldObject instance = null;

	public NullWorldObject() {
		super(null, 0, null, null, null);
	}

	public static NullWorldObject instance() {
		if (instance == null) {
			instance = new NullWorldObject();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
