package org.adamsko.cubicforest.render.cubicModel;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;

import com.badlogic.gdx.graphics.Texture;

interface CubicModelTextureBuilder {

	/**
	 * Create texture from given {@link CubicJsonCube} list.
	 */
	Texture createTexture(List<CubicJsonCube> modelCubes);

	int getTextureSize();

}
