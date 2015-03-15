package org.adamsko.cubicforest.render.world.objectsTextureChanger;

import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Implementing class holds and manages textures of an object. It also manages
 * texture changes.
 */
interface TexturesContainer {

	/**
	 * Add texture to the container.
	 * 
	 * @param objectVisualState
	 *            visual state of an object for which added texture will be
	 *            used. Every state a have number of textures equal to tile
	 *            directions.
	 * @param tileDirection
	 *            tile direction represented by added texture and added visual
	 *            state.
	 */
	void addTexture(TextureRegion textureRegion,
			RenderableObjectVisualState objectVisualState,
			TileDirection tileDirection);

	TextureRegion getTextureRegion(
			RenderableObjectVisualState objectVisualState,
			TileDirection tileDirection);

	/**
	 * Check if container contains textures for given vistual state.
	 */
	boolean containsTextures(RenderableObjectVisualState objectVisualState);

}
