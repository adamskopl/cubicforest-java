package org.adamsko.cubicforest.render.texturesManager;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

/**
 * Inteface for class managing cubic models. Only data about model's cubes read
 * from a model file.
 */
public interface ModelsManager {

	/**
	 * Load data about model with a given name.
	 */
	void loadModel(WorldObjectType modelType);

	List<CubicJsonCube> getModelCubes(WorldObjectType modelType,
			TileDirection tileDirection);
}
