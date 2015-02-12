package org.adamsko.cubicforest.render.cubicModel;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CubicModelsBuilderDefault implements CubicModelsBuilder {

	private final Texture cubesTexture;
	private final List<TextureRegion[]> atlasRows;
	private final int regionW, regionH;

	public CubicModelsBuilderDefault() {
		cubesTexture = new Texture(Gdx.files.internal("data/atomic-atlas.png"));
		this.regionW = 8;
		this.regionH = 8;
		atlasRows = new ArrayList<TextureRegion[]>();
		atlasRows
				.add(new TextureRegion(cubesTexture).split(regionW, regionH)[0]);
	}

	@Override
	public Texture getCubesTexture() {
		return cubesTexture;
	}

	@Override
	public void initTexture(final RenderableObject newObject) {

		final Pixmap pixmap = new Pixmap(30, 30, cubesTexture.getTextureData()
				.getFormat());

		cubesTexture.getTextureData().prepare();
		pixmap.drawPixmap(cubesTexture.getTextureData().consumePixmap(), 0, 0,
				0, 0, 11, 11);
		cubesTexture.getTextureData().disposePixmap();
		cubesTexture.getTextureData().prepare();
		pixmap.drawPixmap(cubesTexture.getTextureData().consumePixmap(), 5, 3,
				0, 0, 11, 11);
		cubesTexture.getTextureData().disposePixmap();

		final Texture test = new Texture(pixmap);

		newObject.setTextureRegion(new TextureRegion(test));
	}
}
