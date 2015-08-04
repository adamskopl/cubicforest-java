package org.adamsko.cubicforest.render.texturesManager;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

public class NullTexturesManager extends TexturesManagerDefault {

	private static NullTexturesManager instance = null;
	private static CTextureRegion nullTexureRegion;

	private NullTexturesManager() {
		super(false);
		nullTexureRegion = NullCTextureRegion.instance();
		CLog.addObject(this, "NullTexturesManager");
		CLog.setUsage(this, true);
	}

	public static NullTexturesManager instance() {
		if (instance == null) {
			instance = new NullTexturesManager();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public CTextureRegion getTextureRegion(
			final WorldObjectType worldObjectType,
			final RenderableObjectVisualState renderableObjectVisualState,
			final TileDirection tileDirection) {
		CLog.error(this, "getTextureRegion");
		return nullTexureRegion;
	}

	@Override
	public void loadTextures(final WorldObjectType objectType) {
		CLog.error(this, "loadTextures " + objectType.toString());
	}
}
