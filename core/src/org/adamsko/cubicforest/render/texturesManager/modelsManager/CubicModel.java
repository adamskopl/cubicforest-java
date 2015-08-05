package org.adamsko.cubicforest.render.texturesManager.modelsManager;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.world.tile.TileDirection;

/**
 * Interface for class holding all information about a single cubic model. Only
 * cubes data.
 */
public interface CubicModel {

	List<CubicJsonCube> getModelCubes(TileDirection tileDirection);

}
