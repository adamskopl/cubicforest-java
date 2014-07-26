package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

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

	public InteractionObjectsMaster(String name, TilesMaster TM,
			WorldObjectType worldObjectsType, String textureName, int tileW,
			int tileH) {
		super(name, TM, worldObjectsType, textureName, tileW, tileH);

	}

	@Override
	public Boolean isTileEventValid(TileEvent eventType, Tile eventTile,
			WorldObject eventObject) {

		WorldObjectType tileObjectType = WorldObjectType.OBJECT_UNDEFINED;

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
	public InteractionResult processTileEvent(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		InteractionResult tileEventResult = interactionResolver
				.resolveInteracion(eventType, eventTile, eventObject);

		return tileEventResult;
	}
}
