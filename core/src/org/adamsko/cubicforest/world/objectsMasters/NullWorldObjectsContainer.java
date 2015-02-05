package org.adamsko.cubicforest.world.objectsMasters;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;

import com.badlogic.gdx.math.Vector2;

public class NullWorldObjectsContainer extends WorldObjectsMasterDefault {

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
	public void loadMapObjects(final List<Vector2> tilePositions) {

	}

	@Override
	public void loadMapObjects(final CFMap map) throws Exception {
	}

	@Override
	public void unloadMapObjects() {
	}

}
