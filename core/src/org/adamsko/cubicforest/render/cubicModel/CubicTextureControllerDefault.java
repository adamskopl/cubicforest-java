package org.adamsko.cubicforest.render.cubicModel;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CubicTextureControllerDefault implements CubicTextureController {

	private Texture cubesTexture;
	private List<TextureRegion[]> atlasRows;

	/**
	 * For {@link NullCubicTextureController}
	 */
	public CubicTextureControllerDefault(final boolean nullContructor) {
	}

	public CubicTextureControllerDefault() {
		final FileHandle textureFileHandle = Gdx.files
				.internal("data/atomic-atlas.png");
		if (!textureFileHandle.exists()) {
			Gdx.app.error("CubicTextureControllerDefault()",
					"!textureFileHandle.exists()");
			return;
		}
		this.cubesTexture = new Texture(textureFileHandle);
		this.atlasRows = new ArrayList<TextureRegion[]>();
		this.atlasRows
				.add(new TextureRegion(this.cubesTexture).split(11, 12)[0]);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public List<TextureRegion[]> getAtlasRows() {
		return atlasRows;
	}
}
