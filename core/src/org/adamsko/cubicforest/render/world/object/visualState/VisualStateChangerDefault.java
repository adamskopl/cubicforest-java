package org.adamsko.cubicforest.render.world.object.visualState;

import org.adamsko.cubicforest.render.world.object.RenderableObject;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class VisualStateChangerDefault implements VisualStateChanger {

	/**
	 * handled object
	 */
	RenderableObject renderableObject;
	RenderableObjectVisualState visualState;
	ObjectsTextureChanger objectsTextureChanger;

	public VisualStateChangerDefault(final RenderableObject renderableObject,
			final ObjectsTextureChanger objectsTextureChanger) {
		this.renderableObject = renderableObject;
		this.visualState = RenderableObjectVisualState.NORMAL;
		this.objectsTextureChanger = objectsTextureChanger;
	}

	@Override
	public void changeState(final RenderableObjectVisualState objectVisualState) {
		this.visualState = objectVisualState;
		final TextureRegion newTextureRegion = objectsTextureChanger
				.getTextureRegion(renderableObject.tileDirection()
						.getDirection(), objectVisualState);
		renderableObject.setTextureRegionCubic(newTextureRegion);
	}

	@Override
	public RenderableObjectVisualState getState() {
		return visualState;
	}
}
