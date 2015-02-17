package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubeColorDefault implements CubeColor {

	private float r;
	private float g;
	private float b;

	@Override
	public void setR(final float r) {
		this.r = r;
	}

	@Override
	public float getR() {
		return r;
	}

	@Override
	public void setG(final float g) {
		this.g = g;
	}

	@Override
	public float getG() {
		return g;
	}

	@Override
	public void setB(final float b) {
		this.b = b;
	}

	@Override
	public float getB() {
		return b;
	}

}
