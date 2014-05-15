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

public abstract class InteractionObjectsMaster extends WorldObjectsContainer implements InteractionMasterClient {

	private RoundsMaster roundsMaster;
	
	public InteractionObjectsMaster(String name, MapsLoader mapsLoader,
			TilesMaster TM, RoundsMaster roundsMaster, WorldObjectType_e worldObjectsType, String textureName,
			int tileW, int tileH) {
		super(name, mapsLoader, TM, worldObjectsType, textureName, tileW, tileH);
		
		this.roundsMaster = roundsMaster;
	}
	
	@Override
	public Boolean isTileEventValid(TileEvent_e eventType, Tile eventTile,
			WorldObject eventObject) {

		WorldObjectType_e tileObjectType = WorldObjectType_e.OBJECT_UNDEFINED;
		
		if(eventTile.hasOccupant()) {			
			tileObjectType = eventTile.getOccupant().getWorldType();
			if(tileObjectType == getWorldObjectsType()) {
				return true;
			}
		}
		
		if(eventTile.hasItem()) {			
			tileObjectType = eventTile.getItem().getWorldType();
			if(tileObjectType == getWorldObjectsType()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * As a result of tile events, change actually issued orders.
	 * 
	 * @param orderOperation
	 */
	protected void orderOperation(OrderOperation_e orderOperation) {
		roundsMaster.orderOperation(orderOperation);
	}

}
