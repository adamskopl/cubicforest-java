package org.adamsko.cubicforest.world.tile.tilesEvents;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault.TileCollisionType;

/**
 * Handles events connected with tiles. For now it's only collision event.
 * 
 * @author adamsko
 */
public interface TilesEventsHandler extends Nullable {

	/**
	 * Handles tile collision.
	 * 
	 * @param evenType
	 *            type of the event
	 * @param eventTile
	 *            tile which is interacting with and object
	 * @param eventObject
	 *            object which is interacting with a tile
	 * @return information how current order should be modified
	 */
	OrderOperationHandler tileCollision(final TileCollisionType evenType,
			final Tile eventTile, final WorldObject eventObject);

}
