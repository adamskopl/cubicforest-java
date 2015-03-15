package org.adamsko.cubicforest.render.world.objectsTextureChanger;

import java.util.HashMap;
import java.util.Map;

import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

class TexturesContainerDefault implements TexturesContainer {

	/**
	 * Holds a {@link TileDirectionTextureCombination} for every visual state.
	 */
	Map<RenderableObjectVisualState, TileDirectionTextureCombination> texturesMap;

	public TexturesContainerDefault() {
		texturesMap = new HashMap<RenderableObjectVisualState, TileDirectionTextureCombination>();
	}

	@Override
	public void addTexture(final TextureRegion textureRegion,
			final RenderableObjectVisualState objectVisualState,
			final TileDirection tileDirection) {

		if (!texturesMap.containsKey(objectVisualState)) {
			texturesMap.put(objectVisualState,
					new TileDirectionTextureCombination());
		}

		texturesMap.get(objectVisualState).addTexture(tileDirection,
				textureRegion);
	}

	@Override
	public TextureRegion getTextureRegion(
			final RenderableObjectVisualState objectVisualState,
			final TileDirection tileDirection) {
		return texturesMap.get(objectVisualState).getTexture(tileDirection);
	}

	@Override
	public boolean containsTextures(
			final RenderableObjectVisualState objectVisualState) {
		return texturesMap.containsKey(objectVisualState);
	}
}
