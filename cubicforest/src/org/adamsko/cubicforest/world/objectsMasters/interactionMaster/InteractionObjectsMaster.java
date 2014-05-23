package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

/**
 * @author adamsko
 *
 */
public abstract class InteractionObjectsMaster extends WorldObjectsContainer
		implements InteractionMasterClient {

	/**
	 * Variable which is set by concrete class. Used to indicate if the current
	 * order should be changed (result returned by OrderOperation_e
	 * processTileEvent())
	 */
	private OrderOperation_e tileEventResult = OrderOperation_e.ORDER_CONTINUE;

	public InteractionObjectsMaster(String name, MapsLoader mapsLoader,
			TilesMaster TM, WorldObjectType_e worldObjectsType,
			String textureName, int tileW, int tileH) {
		super(name, mapsLoader, TM, worldObjectsType, textureName, tileW, tileH);

	}

	@Override
	public Boolean isTileEventValid(TileEvent_e eventType, Tile eventTile,
			WorldObject eventObject) {

		WorldObjectType_e tileObjectType = WorldObjectType_e.OBJECT_UNDEFINED;

		if (eventTile.hasOccupant()) {
			tileObjectType = eventTile.getOccupant().getWorldType();
			if (tileObjectType == getWorldObjectsType()) {
				return true;
			}
		}

		if (eventTile.hasItem()) {
			tileObjectType = eventTile.getItem().getWorldType();
			if (tileObjectType == getWorldObjectsType()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public OrderOperation_e processTileEvent(TileEvent_e eventType,
			Tile eventTile, WorldObject eventObject) {

		// process tile event, set change tileEventResult value if needed
		// (interaction effect affects a process of a current order execution)
		processTileEventImplementation(eventType, eventTile, eventObject);

		OrderOperation_e copy = tileEventResult;
		// set a default value, which will be returned in the case of tile event
		// which has no effect on a current order
		tileEventResult = OrderOperation_e.ORDER_CONTINUE;
		return copy;
	}

	/**
	 * Individual function for processing tile events. Idea: implementation is
	 * issuing orderOperation()
	 * 
	 * Name of the function should be changed. It's a sub-function for
	 * processTileEvent.
	 */
	protected abstract void processTileEventImplementation(
			TileEvent_e eventType, Tile eventTile, WorldObject eventObject);

	/**
	 * Change currently executed order.
	 * 
	 * @param orderOperation
	 */
	protected void orderOperation(OrderOperation_e orderOperation) {
		// tileEventResult is returned in processTileEvent
		tileEventResult = orderOperation;
	}

}
