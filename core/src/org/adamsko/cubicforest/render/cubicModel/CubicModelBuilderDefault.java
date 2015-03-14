package org.adamsko.cubicforest.render.cubicModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.CubicJsonModel;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubePosition;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCubeRenderComparator;
import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;
import org.adamsko.cubicforest.world.tile.TilesHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CubicModelBuilderDefault implements CubicModelBuilder {

	private final CubicModelTextureBuilder cubicModelTextureBuilder;
	private CubicJsonModel cubicJsonModel;
	// original model cubes read from a JSON file
	private List<CubicJsonCube> modelCubes;
	// for every direction a different collection of cubes is held
	private final Map<TilesHelper.TileDirection, List<CubicJsonCube>> modelCubesDirections;
	private final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator;

	private final List<TextureRegion[]> atlasRows;

	public CubicModelBuilderDefault(
			final CubicTextureController cubicTextureController) {
		this.cubicModelTextureBuilder = new CubicModelTextureBuilderDefault(
				cubicTextureController);
		this.modelCubes = null;
		this.cubicJsonCubeRenderComparator = new CubicJsonCubeRenderComparator();
		this.atlasRows = new ArrayList<TextureRegion[]>();
		this.modelCubesDirections = new HashMap<TilesHelper.TileDirection, List<CubicJsonCube>>();
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
		prepareTextures(modelName);
	}

	@Override
	public List<TextureRegion[]> getAtlasRows() {
		return this.atlasRows;
	}

	private void loadCubes(final CubicJsonModel cubicJsonModel) {
		modelCubes = cubicJsonModel.getFrames().get(0).getGroups().get(0)
				.getCubes();

		prepareCubesDirections();

		// sort cubes: sorted list indicates the render order
		Collections.sort(modelCubes, cubicJsonCubeRenderComparator);
	}

	private void prepareCubesDirections() {
		// fill directions map with copies from original model
		for (final TilesHelper.TileDirection d : TilesHelper.TileDirection
				.values()) {
			final List<CubicJsonCube> newCubesDir = new ArrayList<CubicJsonCube>();
			// fill with copies of the original cubes
			for (final CubicJsonCube cube : modelCubes) {
				final CubicJsonCube newCube = new CubicJsonCube(cube);
				newCubesDir.add(newCube);
			}
			modelCubesDirections.put(d, newCubesDir);
		}
		// translate cubes depending from direction
		for (final TilesHelper.TileDirection d : TilesHelper.TileDirection
				.values()) {
			final List<CubicJsonCube> directionCubes = modelCubesDirections
					.get(d);

			for (final CubicJsonCube cube : directionCubes) {
				final CubePosition cubePos = cube.getPos();
				final int originalX = cubePos.getX();
				final int originalY = cubePos.getY();
				switch (d) {
				// default direcion, leave it
				case E:
					break;
				case S:
					cubePos.setX(originalY);
					cubePos.setY(-originalX);
					break;
				case W:
					cubePos.setX(-originalX);
					cubePos.setY(-originalY);
					break;
				case N:
					cubePos.setX(-originalY);
					cubePos.setY(originalX);
					break;
				}
			}

			Collections.sort(modelCubesDirections.get(d),
					cubicJsonCubeRenderComparator);
		}
	}

	private void prepareTextures(final String TEMPNAME) {
		this.atlasRows.add(new TextureRegion(cubicModelTextureBuilder
				.createTexture(
						modelCubesDirections.get(TilesHelper.TileDirection.S),
						TEMPNAME)).split(
				cubicModelTextureBuilder.getTextureSize(),
				cubicModelTextureBuilder.getTextureSize())[0]);
	}
}
