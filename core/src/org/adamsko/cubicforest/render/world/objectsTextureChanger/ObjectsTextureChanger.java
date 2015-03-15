package org.adamsko.cubicforest.render.world.objectsTextureChanger;

import org.adamsko.cubicforest.render.world.object.RenderableObject;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Changes textures of {@link RenderableObject} objects.
 */
public interface ObjectsTextureChanger {

	TextureRegion tempGetCubicTextureRegion();

	TextureRegion getTextureRegion(TileDirection tileDirection,
			RenderableObjectVisualState objectVisualState);

}
