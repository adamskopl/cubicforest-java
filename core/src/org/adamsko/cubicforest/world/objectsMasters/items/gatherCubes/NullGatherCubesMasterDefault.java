package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

public class NullGatherCubesMasterDefault extends GatherCubesMasterDefault {
	private static NullGatherCubesMasterDefault instance = null;

	private NullGatherCubesMasterDefault() {
		super();
	}

	public static NullGatherCubesMasterDefault instance() {
		if (instance == null) {
			instance = new NullGatherCubesMasterDefault();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
