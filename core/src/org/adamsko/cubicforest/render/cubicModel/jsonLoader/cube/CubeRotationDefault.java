package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubeRotationDefault implements CubeRotation {
	private float x;
	private float y;
	private float z;

	@Override
	public void setX(final float x) {
		this.x = x;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public void setY(final float y) {
		this.y = y;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setZ(final float z) {
		this.z = z;
	}

	@Override
	public float getZ() {
		return z;
	}
}
