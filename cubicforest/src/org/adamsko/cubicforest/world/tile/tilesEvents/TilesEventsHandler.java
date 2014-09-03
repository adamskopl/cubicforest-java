package org.adamsko.cubicforest.world.tile.tilesEvents;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;

/**
 * Handles events described by {@link TileEvent}
 * 
 * @author adamsko
 */
public interface TilesEventsHandler {

	/**
	 * Handles event connected with a tile.
	 * 
	 * @param evenType
	 *            type of the event
	 * @param eventTile
	 *            tile which is interacting with and object
	 * @param eventObject
	 *            object which is interacting with a tile
	 * @return information how current order should be modified
	 */
	OrderOperationHandler tileEvent(final TileEvent evenType,
			final Tile eventTile, final WorldObject eventObject);

}
