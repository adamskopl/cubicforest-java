package org.adamsko.cubicforest.render.texturesManager.cubicTextureController;

import org.adamsko.cubicforest.Nullable;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An interface for a class managing the only texture in a game: cubes texture.
 * Controller exposes this texture for classes generating textures of the cubic
 * objects.
 */
public interface CubicTextureController extends Nullable {

	TextureRegion getTextureRegion(String color, int rowIndex);

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
