package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubeColor {
	private int r;
	private int g;
	private int b;

	public void setR(final int r) {
		this.r = r;
	}

	public float getR() {
		return r;
	}

	public void setG(final int g) {
		this.g = g;
	}

	public float getG() {
		return g;
	}

	public void setB(final int b) {
		this.b = b;
	}

	public int getB() {
		return b;
	}

	public String toRGBString() {
		return new String(Integer.toString(r) + "_" + Integer.toString(g) + " "
				+ Integer.toString(b));
	}
}
