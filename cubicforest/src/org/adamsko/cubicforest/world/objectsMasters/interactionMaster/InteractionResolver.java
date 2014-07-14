package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

/**
 * @author adamsko
 * 
 */
public interface InteractionResolver {

	/**
	 * Resolve interaction for event passed to related Master (e.g.
	 * InteractionResolverGatherCubes resolves event related to
	 * GatherCubesMaster).
	 * 
	 * @param eventType
	 * @param eventTile
	 *            tile on which object from related Master should be
	 * @param eventObject
	 *            object which is interacting with a tile
	 * @return
	 */
	InteractionResult resolveInteracion(TileEvent eventType, Tile eventTile,
			WorldObject eventObject);
	
	InteractionResolverType_e getType();

}
