package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubicJsonCubeDefault implements CubicJsonCube {

	private String name;
	private CubePosition pos;
	private CubeRotation rot;
	private CubeColor col;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public CubePosition getPos() {
		return pos;
	}

	@Override
	public void setPos(final CubePosition cubePosition) {
		this.pos = cubePosition;
	}

	@Override
	public CubeRotation getRot() {
		return rot;
	}

	@Override
	public void setRot(final CubeRotation cubeRotation) {
		this.rot = cubeRotation;
	}

	@Override
	public CubeColor getCol() {
		return col;
	}

	@Override
	public void setCol(final CubeColor cubeColor) {
		this.col = cubeColor;
	}

}
