package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubeRotation {
	private float x;
	private float y;
	private float z;

	public CubeRotation(final CubeRotation cubeRotation) {
		this.x = cubeRotation.getX();
		this.y = cubeRotation.getY();
		this.z = cubeRotation.getZ();
	}

	public CubeRotation(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(final float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public void setY(final float y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}

	public void setZ(final float z) {
		this.z = z;
	}

	public float getZ() {
		return z;
	}
}
