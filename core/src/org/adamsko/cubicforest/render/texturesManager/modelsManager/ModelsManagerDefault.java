package org.adamsko.cubicforest.render.texturesManager.modelsManager;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ModelsManagerDefault implements ModelsManager {

	private final Map<WorldObjectType, CubicModel> modelsJson;
	private final Map<Vector2, CubicModelGeneric> modelsDimensions;
	private final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator;

	// help variables
	private final Vector2 vector2;

	public ModelsManagerDefault() {
		modelsJson = new HashMap<WorldObjectType, CubicModel>();
		modelsDimensions = new HashMap<Vector2, CubicModelGeneric>();
		this.cubicJsonCubeRenderComparator = new CubicJsonCubeRenderComparator();
		this.vector2 = new Vector2();

		CLog.addObject(this, "ModelsManager");
		CLog.setUsage(this, true);
	}

	@Override
	public void loadModel(final WorldObjectType modelType) {
		if (modelsJson.containsKey(modelType)) {
			// CLog.error(this, "load model " + modelType.toString()
			// + "already loaded");
			return;
		}
		final CubicModel model = new CubicModelDefault(modelType.toString(),
				this.cubicJsonCubeRenderComparator);
		modelsJson.put(modelType, model);
	}

	@Override
	public void loadModel(final int width, final int height) {
		vector2.set(width, height);
		if (modelsDimensions.containsKey(vector2)) {
			// CLog.error(this, "load model " + vector2.toString()
			// + "already loaded");
			return;
		}
		final CubicModelGeneric model = new CubicModelGenericDefault(width,
				height);
		modelsDimensions.put(vector2, model);
	}

	@Override
	public List<CubicJsonCube> getModelCubes(final WorldObjectType modelType,
			final TileDirection tileDirection) {
		if (!modelsJson.containsKey(modelType)) {
			Gdx.app.error("ModelsManagerDefault::getModelCubes", "no model "
					+ modelType.toString());
			return null;
		}
		return modelsJson.get(modelType).getModelCubes(tileDirection);
	}

	@Override
	public List<CubicJsonCube> getModelCubes(final int width, final int height) {
		vector2.set(width, height);
		if (!modelsDimensions.containsKey(vector2)) {
			Gdx.app.error("ModelsManagerDefault::getModelCubes", "no model "
					+ vector2.toString());
			return null;
		}
		return modelsDimensions.get(vector2).getModelCubes();
	}
}
