package org.adamsko.cubicforest.render.texturesManager.modelsManager;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubePosition;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;

public class CubicModelGenericDefault implements CubicModelGeneric {

	private final List<CubicJsonCube> modelCubes;

	public CubicModelGenericDefault(final int width, final int height) {
		this.modelCubes = new ArrayList<CubicJsonCube>();
		loadModel(width, height);
	}

	@Override
	public List<CubicJsonCube> getModelCubes() {
		return modelCubes;
	}

	private void loadModel(final int width, final int height) {

		/*
		 * COnsidering models loaded from json: height is 'z' and goes up from
		 * 0. 'x' and 'y' are symmetric.
		 */

		int xPos;
		for (int h = 0; h < height; h++) {
			xPos = -width / 2;
			for (int w = 0; w < width; w++) {
				final CubicJsonCube cube = new CubicJsonCube();
				cube.setPos(new CubePosition(xPos, 0, h));
				cube.setColName("darkgray");
				modelCubes.add(cube);
				xPos++;
			}
		}
	}
}
