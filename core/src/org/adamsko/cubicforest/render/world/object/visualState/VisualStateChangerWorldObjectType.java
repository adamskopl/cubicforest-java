package org.adamsko.cubicforest.render.world.object.visualState;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.object.RenderableObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;

public class VisualStateChangerWorldObjectType implements VisualStateChanger {

	/**
	 * handled object
	 */
	private final RenderableObject renderableObject;
	private final WorldObjectType worldObjectType;
	RenderableObjectVisualState visualState;
	private final TexturesManager texturesManager;

	// help variables
	CTextureRegion newTextureRegion;

	public VisualStateChangerWorldObjectType(
			final RenderableObject renderableObject,
			final TexturesManager texturesManager,
			final WorldObjectType worldObjectType) {
		this.renderableObject = renderableObject;
		this.worldObjectType = worldObjectType;
		this.visualState = RenderableObjectVisualState.NORMAL;
		this.texturesManager = texturesManager;

		CLog.addObject(this, "VisualStateChanger");
		CLog.setUsage(this, false);
	}

	@Override
	public void changeState(final RenderableObjectVisualState objectVisualState) {
		this.visualState = objectVisualState;

		newTextureRegion = texturesManager.getTextureRegion(worldObjectType,
				objectVisualState, renderableObject.tileDirection()
						.getDirection());

		renderableObject.setTextureRegion(newTextureRegion);
		CLog.debug(this, "changeState " + worldObjectType.toString());
	}

	@Override
	public RenderableObjectVisualState getState() {
		return visualState;
	}
}
