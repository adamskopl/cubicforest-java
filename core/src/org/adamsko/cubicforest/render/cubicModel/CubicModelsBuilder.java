package org.adamsko.cubicforest.render.cubicModel;

import org.adamsko.cubicforest.render.world.RenderableObject;

import com.badlogic.gdx.graphics.Texture;

/**
 * Interface for class shared by {@link CubicModel} classes. Exposes texture
 * atlas with atomic cubes.
 */
public interface CubicModelsBuilder {

	Texture getCubesTexture();

	void initTexture(final RenderableObject newObject);

}
