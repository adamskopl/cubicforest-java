package org.adamsko.cubicforest.render.cubicModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.CubicJsonModel;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCubeRenderComparator;
import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CubicModelBuilderDefault implements CubicModelBuilder {

	private final CubicModelTextureBuilder cubicModelTextureBuilder;
	private CubicJsonModel cubicJsonModel;
	private List<CubicJsonCube> modelCubes;
	private final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator;

	private final List<TextureRegion[]> atlasRows;

	public CubicModelBuilderDefault(
			final CubicTextureController cubicTextureController) {
		this.cubicModelTextureBuilder = new CubicModelTextureBuilderDefault(
				cubicTextureController);
		this.modelCubes = null;
		this.cubicJsonCubeRenderComparator = new CubicJsonCubeRenderComparator();
		this.atlasRows = new ArrayList<TextureRegion[]>();
	}

	@Override
	public void loadModel(final String modelName) {
		final String filePath = new String("data/models/" + modelName + ".json");
		final FileHandle fileHandle = Gdx.files.internal(filePath);
		if (!fileHandle.exists()) {
			Gdx.app.error("CubicModelBuilderDefault::loadModel()", modelName
					+ " reading error");
			return;
		}
		final String fileString = fileHandle.readString();
		try {
			this.cubicJsonModel = new Gson().fromJson(fileString,
					CubicJsonModel.class);
		} catch (final JsonSyntaxException ex) {
			Gdx.app.error("CubicModelBuilderDefault::loadModel()",
					ex.toString());
			return;
		}
		// model loaded, read cubes and sort them
		loadCubes(this.cubicJsonModel);
		prepareTextures();
	}

	@Override
	public List<TextureRegion[]> getAtlasRows() {
		return this.atlasRows;
	}

	private void loadCubes(final CubicJsonModel cubicJsonModel) {
		modelCubes = cubicJsonModel.getFrames().get(0).getGroups().get(0)
				.getCubes();
		// sort cubes: sorted list indicates the render order
		Collections.sort(modelCubes, cubicJsonCubeRenderComparator);
	}

	private void prepareTextures() {
		this.atlasRows.add(new TextureRegion(cubicModelTextureBuilder
				.createTexture(modelCubes)).split(
				cubicModelTextureBuilder.getTextureSize(),
				cubicModelTextureBuilder.getTextureSize())[0]);
	}
}
