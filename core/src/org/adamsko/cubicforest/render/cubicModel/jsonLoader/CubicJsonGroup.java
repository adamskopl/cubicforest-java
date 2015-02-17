package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;

/**
 * Interface for class describing one group of cubes in the Blender model.
 * Groups are used to indicate different parts of the model, for e.g. special
 * effects purposes.
 */
public interface CubicJsonGroup {

	String getName();

	void setName(String name);

	List<CubicJsonCube> getCubes();

	void setCubes(List<CubicJsonCube> cubes);

}
