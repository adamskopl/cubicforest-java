package org.adamsko.cubicforest.render.texturesManager.ModelsTexturesContainer;

import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

public interface ModelsTexturesContainer {

	/**
	 * Get texture region for given parameters. If no texture: return null.
	 */
	CTextureRegion get(WorldObjectType worldObjectType,
			RenderableObjectVisualState renderableObjectVisualState,
			TileDirection tileDirection);

	void put(WorldObjectType worldObjectType,
			RenderableObjectVisualState renderableObjectVisualState,
			TileDirection tileDirection, CTextureRegion textureRegion);
}
