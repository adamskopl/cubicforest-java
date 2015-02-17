package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;

public class CubicJsonGroup {

	/**
	 * Group's name.
	 */
	private String name;

	private List<CubicJsonCube> cubes;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<CubicJsonCube> getCubes() {
		return cubes;
	}

	public void setCubes(final List<CubicJsonCube> cubes) {
		this.cubes = cubes;
	}

}
