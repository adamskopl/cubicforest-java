package org.adamsko.cubicforest.render.cubicModel;

/**
 * An interface for a class describing model built from atomic cubes.
 */
public interface CubicModel {

	/**
	 * Add new cube to the model. Cube is defining its position in a model.
	 */
	void addCube(CubicAtom cube);

}
