package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

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
	public OrderOperation_e processTileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject);

	/**
	 * @param evenType
	 * @param eventTile
	 * @param eventObject
	 * @return
	 */
	public Boolean isTileEventValid(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject);

}
