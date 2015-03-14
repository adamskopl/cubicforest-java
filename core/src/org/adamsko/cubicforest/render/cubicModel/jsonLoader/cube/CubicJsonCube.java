package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

public class CubicJsonCube {

	private String name;
	private CubePosition pos;
	private CubeRotation rot;
	// color name
	private String colName;

	public CubicJsonCube(final CubicJsonCube cube) {
		this.name = new String(cube.getName());
		this.pos = new CubePosition(cube.getPos());
		this.rot = new CubeRotation(cube.getRot());
		this.colName = new String(cube.getColName());
	}

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

	public String getColName() {
		return this.colName;
	}

	public void setColName(final String cubeColName) {
		this.colName = cubeColName;
	}

	/**
	 * Compares position of the cube with the position of the other cube.
	 * 
	 * @return true if positions are equal
	 */
	boolean equalPos(final CubicJsonCube other) {
		final CubePosition posOther = other.getPos();
		return (pos.getX() == posOther.getX() && pos.getY() == posOther.getY() && pos
				.getZ() == posOther.getZ());
	}

}
