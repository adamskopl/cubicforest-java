package org.adamsko.cubicforest.world.objectsMasters.collisionsMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

/**
 * @author adamsko
 * 
 */
public interface CollisionResolver {

	/**
	 * Resolve collision for event passed to related Master (e.g.
	 * CollisionResolverGatherCubes resolves event related to
	 * GatherCubesMaster).
	 * 
	 * @param eventType
	 * @param eventTile
	 *            tile on which object from related Master should be
	 * @param eventObject
	 *            object which is interacting with a tile
	 * @return
	 */
	CollisionResult resolveInteracion(TileEvent eventType, Tile eventTile,
			WorldObject eventObject);

	CollisionResolverType_e getType();

}
