package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;

/**
 * {@link GatherCube} objects manager.
 * 
 * @author adamsko
 * 
 */
public interface GatherCubesMaster extends Nullable, WorldObjectsMaster,
		RenderableObjectsMaster {

	/**
	 * Get {@link GatherCubesCounter} for gather cubes amount manipulation
	 */
	GatherCubesCounter getGatherCubesCounter();

	/**
	 * Change cube's texture.
	 * 
	 * @param cube
	 *            cube to be changed
	 */
	void cubeHighlight(final GatherCube cube);

	/**
	 * Change cube's texture to its original one
	 * 
	 * @param cube
	 *            cube to be changed
	 */
	void cubeUnHighlight(final GatherCube cube);

}
