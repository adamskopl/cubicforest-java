package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public abstract class InteractionObjectsMaster extends WorldObjectsContainer implements InteractionMasterClient {

	public InteractionObjectsMaster(MapsLoader mapsLoader, TilesMaster TM, WorldObjectType_e worldObjectsType, String textureName,
			int tileW, int tileH) {
		super(mapsLoader, TM, worldObjectsType, textureName, tileW, tileH);
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

}
