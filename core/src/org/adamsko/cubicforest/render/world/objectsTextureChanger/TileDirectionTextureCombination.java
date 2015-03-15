package org.adamsko.cubicforest.render.world.objectsTextureChanger;

import java.util.HashMap;
import java.util.Map;

import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Combination of a {@link TileDirection} and {@link TextureRegion}.
 */
class TileDirectionTextureCombination {

	Map<TileDirection, TextureRegion> directionMap;

	public TileDirectionTextureCombination() {
		this.directionMap = new HashMap<TileDirection, TextureRegion>();
	}

	public void addTexture(final TileDirection tileDirection,
			final TextureRegion textureRegion) {
		if (directionMap.containsKey(tileDirection)) {
			Gdx.app.error("TileDirectionTextureCombination::addTexture()",
					tileDirection.name() + " already in a map");
			return;
		}
		directionMap.put(tileDirection, textureRegion);
	}

	TextureRegion getTexture(final TileDirection tileDirection) {
		return directionMap.get(tileDirection);
	}
}
