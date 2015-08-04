package org.adamsko.cubicforest.render.texturesManager;

import org.adamsko.cubicforest.Nullable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CTextureRegion extends TextureRegion implements Nullable {

	public CTextureRegion(final Texture texture) {
		super(texture);
	}

	public CTextureRegion() {
		super();
	}

	@Override
	public boolean isNull() {
		return false;
	}

}
