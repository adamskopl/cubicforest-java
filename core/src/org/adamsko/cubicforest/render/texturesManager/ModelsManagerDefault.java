package org.adamsko.cubicforest.render.texturesManager;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCubeRenderComparator;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.Gdx;

public class ModelsManagerDefault implements ModelsManager {

	private final Map<WorldObjectType, CubicModel> models;
	private final Comparator<CubicJsonCube> cubicJsonCubeRenderComparator;

	public ModelsManagerDefault() {
		models = new HashMap<WorldObjectType, CubicModel>();
		this.cubicJsonCubeRenderComparator = new CubicJsonCubeRenderComparator();
	}

	@Override
	public void loadModel(final WorldObjectType modelType) {
		final CubicModel model = new CubicModelDefault(modelType.toString(),
				this.cubicJsonCubeRenderComparator);
		models.put(modelType, model);
	}

	@Override
	public List<CubicJsonCube> getModelCubes(final WorldObjectType modelType,
			final TileDirection tileDirection) {
		if (!models.containsKey(modelType)) {
			Gdx.app.error("ModelsManagerDefault::getModelCubes", "no model "
					+ modelType.toString());
			return null;
		}
		return models.get(modelType).getModelCubes(tileDirection);
	}
}
