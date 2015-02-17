package org.adamsko.cubicforest.render.cubicModel;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.CubicJsonModel;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CubicModelBuilderDefault implements CubicModelBuilder {

	private final CubicTextureController cubicTextureController;
	private CubicJsonModel cubicJsonModel;
	private final List<CubicJsonCube> modelCubes;

	public CubicModelBuilderDefault(
			final CubicTextureController cubicTextureController) {
		this.cubicTextureController = cubicTextureController;
		this.modelCubes = new ArrayList<CubicJsonCube>();
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
			cubicJsonModel = new Gson().fromJson(fileString,
					CubicJsonModel.class);
		} catch (final JsonSyntaxException ex) {
			Gdx.app.error("CubicModelBuilderDefault::loadModel()",
					ex.toString());
			return;
		}
	}

	@Override
	public List<TextureRegion[]> getAtlasRows() {
		return cubicTextureController.getAtlasRows();
	}
}
