package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;

public class CubicJsonGroupDefault implements CubicJsonGroup {

	/**
	 * Group's name.
	 */
	private String name;

	private List<CubicJsonCube> cubes;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public List<CubicJsonCube> getCubes() {
		return cubes;
	}

	@Override
	public void setCubes(final List<CubicJsonCube> cubes) {
		this.cubes = cubes;
	}

}
