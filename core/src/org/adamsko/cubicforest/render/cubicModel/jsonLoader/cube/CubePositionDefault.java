package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubePositionDefault implements CubePosition {
	private int x;
	private int y;
	private int z;

	@Override
	public void setX(final int x) {
		this.x = x;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setY(final int y) {
		this.y = y;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setZ(final int z) {
		this.z = z;
	}

	@Override
	public int getZ() {
		return z;
	}
}
