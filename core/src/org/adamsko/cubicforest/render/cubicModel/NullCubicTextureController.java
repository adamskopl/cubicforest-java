package org.adamsko.cubicforest.render.cubicModel;

public class NullCubicTextureController extends CubicTextureControllerDefault {
	private static NullCubicTextureController instance = null;

	private NullCubicTextureController() {
		super(true);
	}

	public static NullCubicTextureController instance() {
		if (instance == null) {
			instance = new NullCubicTextureController();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
