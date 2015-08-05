package org.adamsko.cubicforest.render.world.object.visualState;

import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.object.RenderableObject;

public class VisualStateChangerGeneric implements VisualStateChanger {

	private final RenderableObject renderableObject;
	RenderableObjectVisualState visualState;
	final int width, height;
	private final TexturesManager texturesManager;

	// help variables
	CTextureRegion newTextureRegion;

	public VisualStateChangerGeneric(final RenderableObject renderableObject,
			final TexturesManager texturesManager, final int width,
			final int height) {
		this.width = width;
		this.height = height;
		this.renderableObject = renderableObject;
		this.texturesManager = texturesManager;
	}

	@Override
	public void changeState(final RenderableObjectVisualState objectVisualState) {
		this.visualState = objectVisualState;
		newTextureRegion = texturesManager.getTextureRegion(width, height,
				objectVisualState);
		renderableObject.setTextureRegion(newTextureRegion);
	}

	@Override
	public RenderableObjectVisualState getState() {
		return visualState;
	}

}
