package org.adamsko.cubicforest.render.texturesManager;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

/**
 * Interface for main class managing textures.
 */
public interface TexturesManager extends Nullable {

	/**
	 * Load textures for objects of given type.
	 */
	// public void loadTextures(WorldObjectType objectType);

	public CTextureRegion getTextureRegion(WorldObjectType worldObjectType,
			RenderableObjectVisualState objectVisualState,
			TileDirection tileDirection);

	public CTextureRegion getTextureRegion(int width, int height,
			RenderableObjectVisualState objectVisualState);

}
