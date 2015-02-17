package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubicJsonCube {

	private String name;
	private CubePosition pos;
	private CubeRotation rot;
	private CubeColor col;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CubePosition getPos() {
		return pos;
	}

	public void setPos(final CubePosition cubePosition) {
		this.pos = cubePosition;
	}

	public CubeRotation getRot() {
		return rot;
	}

	public void setRot(final CubeRotation cubeRotation) {
		this.rot = cubeRotation;
	}

	public CubeColor getCol() {
		return col;
	}

	public void setCol(final CubeColor cubeColor) {
		this.col = cubeColor;
	}

}
