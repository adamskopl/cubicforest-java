package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

/**
 * Interface for class describing one cube of the Blender model.
 */
public interface CubicJsonCube {

	String getName();

	void setName(String name);

	CubePosition getPos();

	void setPos(CubePosition cubePosition);

	CubeRotation getRot();

	void setRot(CubeRotation cubeRotation);

	CubeColor getCol();

	void setCol(CubeColor cubeColor);

}
