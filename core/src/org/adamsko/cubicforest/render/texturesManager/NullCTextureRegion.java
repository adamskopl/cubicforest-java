package org.adamsko.cubicforest.render.texturesManager;

import org.adamsko.cubicforest.helpTools.CLog;

import com.badlogic.gdx.graphics.Texture;

public class NullCTextureRegion extends CTextureRegion {

	private static NullCTextureRegion instance = null;

	private NullCTextureRegion() {
		CLog.addObject(this, "NullCTextureRegion");
		CLog.setUsage(this, true);
	}

	public static NullCTextureRegion instance() {
		if (instance == null) {
			instance = new NullCTextureRegion();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public Texture getTexture() {
		CLog.error(this, "getTexture");
		return super.getTexture();
	}

}
