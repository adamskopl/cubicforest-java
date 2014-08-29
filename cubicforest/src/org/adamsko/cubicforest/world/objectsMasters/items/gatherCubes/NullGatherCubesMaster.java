package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

public class NullGatherCubesMaster extends GatherCubesMaster {
	private static NullGatherCubesMaster instance = null;

	private NullGatherCubesMaster() {
		super();
	}

	public static NullGatherCubesMaster instance() {
		if (instance == null) {
			instance = new NullGatherCubesMaster();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
