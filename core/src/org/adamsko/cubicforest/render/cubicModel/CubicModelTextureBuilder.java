package org.adamsko.cubicforest.render.cubicModel;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

interface CubicModelTextureBuilder {

	/**
	 * Create texture from given {@link CubicJsonCube} list.
	 * 
	 * @param desiredCubesColor
	 *            color's name indicating texture's name from which cubes are
	 *            taken. Empty if colors should be taken from cubes provided in
	 *            modelCubes variable.
	 */
	TextureRegion createTexture(List<CubicJsonCube> modelCubes,
			String desiredCubesColor);

	int getTextureSize();

}
