package org.adamsko.cubicforest.render.cubicModel.texturesController;

import java.util.List;

import org.adamsko.cubicforest.Nullable;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An interface for a class managing the only texture in a game: cubes texture.
 * Controller exposes this texture for classes generating textures of the cubic
 * objects.
 */
public interface CubicTextureController extends Nullable {

	/**
	 * Return atlas rows of cubes for given color.
	 * 
	 * @param color
	 *            a name of a desired color
	 */
	List<TextureRegion[]> getAtlasRowsColor(String color);

	Format getPixelFormat();

	/**
	 * Get width of a single cube texture region in an atlas.
	 */
	int getCubeTexW();

	/**
	 * Get height of a single cube texture region in an atlas.
	 */
	int getCubeTexH();

}
