package org.adamsko.cubicforest.render.texturesManager.texturesGenerator;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;

/**
 * Interface for a class generating textures.
 */
public interface TexturesGenerator {

	CTextureRegion generate(final List<CubicJsonCube> modelCubes,
			RenderableObjectVisualState renderableObjectVisualState,
			boolean isometric);

}
