package org.adamsko.cubicforest.render.cubicModel;

import java.util.List;

import org.adamsko.cubicforest.Nullable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An interface for a class managing the only texture in a game: cubes texture.
 * Controller exposes this texture for classes generating textures of the cubic
 * objects.
 */
public interface CubicTextureController extends Nullable {

	List<TextureRegion[]> getAtlasRows();

}
