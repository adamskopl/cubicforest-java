package org.adamsko.cubicforest.render.cubicModel;

import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Interface for class creating cubic models: reading model files, and providing
 * texture informations for other. Does not keep any data: builder only creates
 * textures for other classes.
 */
public interface CubicModelBuilder {

	void loadModel(String modelName);

	/**
	 * Create {@link TextureRegion} from loaded cubes.
	 * 
	 * @param tileDirection
	 *            indicates for which direction texture should be created.
	 * @param colorName
	 *            color name indicating texture from which cubes will be loaded.
	 *            Empty if colors should be taken from loaded cubes.
	 */
	TextureRegion createTextureRegion(TileDirection tileDirection,
			String colorName);

}
