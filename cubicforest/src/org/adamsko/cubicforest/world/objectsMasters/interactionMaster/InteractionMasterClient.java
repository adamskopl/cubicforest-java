package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public interface InteractionMasterClient {

	/**
	 * Interaction: an {@link WorldObject} is interacting with a tile in some
	 * way.
	 */
	public void tileEvent(TileEvent_e evenType, Tile eventTile,
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
