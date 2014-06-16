package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

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

	protected InteractionResolver interactionResolver;

	public void setInteractionResolver(InteractionResolver interactionResolver) {
		this.interactionResolver = interactionResolver;
	}

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
	public InteractionResult processTileEvent(TileEvent_e eventType,
			Tile eventTile, WorldObject eventObject) {

		InteractionResult tileEventResult = interactionResolver
				.resolveInteracion(eventType, eventTile, eventObject);

		return tileEventResult;
	}
}
