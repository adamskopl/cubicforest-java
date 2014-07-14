package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

/**
 * Client which will resolve tile event. Each client is responsible for
 * particular type of object occupying event tile (tile which is interacting
 * with an active object). E.g. EnemiesMaster is responsible for events for
 * enemy occupying event tile.
 * 
 * @author adamsko
 * 
 */
public interface InteractionMasterClient {

	/**
	 * Interaction: an {@link WorldObject} is interacting with a tile in some
	 * way.
	 * 
	 * @param evenType
	 * @param eventTile
	 * @param eventObject
	 * @return the effect of the interaction: should current order be modified?
	 *         E.g object should stop, because of the interaction with object,
	 *         which stops other objects.
	 */
	public InteractionResult processTileEvent(TileEvent evenType,
			Tile eventTile, WorldObject eventObject);

	/**
	 * Check if client is responsible for resolving interaction event of an
	 * object occupying tile.
	 * 
	 * @param evenType
	 * @param eventTile
	 * @param eventObject
	 * @return
	 */
	public Boolean isTileEventValid(TileEvent evenType, Tile eventTile,
			WorldObject eventObject);

}
