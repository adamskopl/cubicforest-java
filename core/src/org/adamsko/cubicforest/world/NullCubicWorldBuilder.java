package org.adamsko.cubicforest.world;

public class NullCubicWorldBuilder extends CubicWorldBuilder {

	private static NullCubicWorldBuilder instance = null;

	private NullCubicWorldBuilder() {
		super(0);
	}

	public static NullCubicWorldBuilder instance() {
		if (instance == null) {
			instance = new NullCubicWorldBuilder();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
