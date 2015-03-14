package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubePosition {
	private int x;
	private int y;
	private int z;

	public CubePosition(final CubePosition cubePosition) {
		this.x = cubePosition.getX();
		this.y = cubePosition.getY();
		this.z = cubePosition.getZ();
	}

	public CubePosition(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setZ(final int z) {
		this.z = z;
	}

	public int getZ() {
		return z;
	}
}
