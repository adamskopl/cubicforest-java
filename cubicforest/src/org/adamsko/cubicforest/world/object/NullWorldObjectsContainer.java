package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;

public class NullWorldObjectsContainer extends WorldObjectsContainer {

	private static NullWorldObjectsContainer instance = null;

	private NullWorldObjectsContainer() {
		super(0);
	}

	public static NullWorldObjectsContainer instance() {
		if (instance == null) {
			instance = new NullWorldObjectsContainer();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public void loadMapObjects(final CFMap map) throws Exception {
	}

	@Override
	public void unloadMapObjects() throws Exception {
	}

}
