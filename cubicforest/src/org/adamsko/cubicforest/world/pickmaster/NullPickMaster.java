package org.adamsko.cubicforest.world.pickmaster;


public class NullPickMaster extends PickMaster {

	private static NullPickMaster instance = null;

	private NullPickMaster() {
		super();
	}

	public static NullPickMaster instance() {
		if (instance == null) {
			instance = new NullPickMaster();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
