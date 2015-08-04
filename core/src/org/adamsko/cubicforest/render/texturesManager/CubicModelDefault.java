package org.adamsko.cubicforest.render.texturesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.adamsko.cubicforest.render.cubicModel.CubicGsonModel;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubePosition;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CubicModelDefault implements CubicModel {

	// for every direction a different collection of cubes is held
	private final Map<TileDirection, List<CubicJsonCube>> modelCubesDirections;

	/**
	 * Constructor load model with a given name.
	 *
	 * @param modelName
	 */
	public CubicModelDefault(final String modelName,
			final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator) {
		this.modelCubesDirections = new HashMap<TileDirection, List<CubicJsonCube>>();
		loadModel(modelName, cubicJsonCubeRenderComparator);
	}

	@Override
	public List<CubicJsonCube> getModelCubes(final TileDirection tileDirection) {
		return modelCubesDirections.get(tileDirection);
	}

	private void loadModel(final String modelName,
			final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator) {
		final String filePath = new String("data/models/" + modelName + ".json");
		final FileHandle fileHandle = Gdx.files.internal(filePath);
		if (!fileHandle.exists()) {
			Gdx.app.error("CubicModelBuilderDefault::loadModel()", modelName
					+ " reading error");
			return;
		}
		final String fileString = fileHandle.readString();
		try {
			CubicGsonModel cubicGsonModel = new Gson().fromJson(fileString,
					CubicGsonModel.class);

			if (cubicGsonModel != null) {
				List<CubicJsonCube> modelCubes = cubicGsonModel.getFrames()
						.get(0).getGroups().get(0).getCubes();

				prepareCubesDirections(modelCubes,
						cubicJsonCubeRenderComparator);
				cubicGsonModel = null;
				modelCubes = null;
			}

		} catch (final JsonSyntaxException ex) {
			Gdx.app.error("CubicModelBuilderDefault::loadModel()",
					ex.toString());
			return;
		}
	}

	private void prepareCubesDirections(final List<CubicJsonCube> modelCubes,
			final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator) {
		// fill directions map with copies from original model. they will be
		// rearranged for every direction.
		for (final TileDirection d : TileDirection.values()) {
			final List<CubicJsonCube> newCubesDir = new ArrayList<CubicJsonCube>();
			// fill with copies of the original cubes
			for (final CubicJsonCube cube : modelCubes) {
				final CubicJsonCube newCube = new CubicJsonCube(cube);
				newCubesDir.add(newCube);
			}
			modelCubesDirections.put(d, newCubesDir);
		}

		sortDirectionCubes(cubicJsonCubeRenderComparator);
	}

	private void sortDirectionCubes(
			final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator) {
		// translate cubes depending from direction
		for (final TileDirection d : TileDirection.values()) {
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
}
