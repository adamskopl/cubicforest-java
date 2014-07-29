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

	private InteractionResolver interactionResolver;

	public void setInteractionResolver(
			final InteractionResolver interactionResolver) {
		this.interactionResolver = interactionResolver;
	}

	public InteractionObjectsMaster(final String name, final TilesMaster TM,
			final WorldObjectType type, final String textureName,
			final int tileW, final int tileH) {
		super(name, type, TM, textureName, tileW, tileH);

	}

	@Override
	public Boolean isTileEventValid(final TileEvent eventType,
			final Tile eventTile, final WorldObject eventObject) {

		WorldObjectType tileObjectType = WorldObjectType.OBJECT_UNDEFINED;

		if (eventTile.hasOccupant()) {
			tileObjectType = eventTile.getOccupant().getType();
			if (tileObjectType == getType()) {
				return true;
			}
		}

		if (eventTile.hasItem()) {
			tileObjectType = eventTile.getItem().getType();
			if (tileObjectType == getType()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public InteractionResult processTileEvent(final TileEvent eventType,
			final Tile eventTile, final WorldObject eventObject) {

		final InteractionResult tileEventResult = interactionResolver
				.resolveInteracion(eventType, eventTile, eventObject);

		return tileEventResult;
	}
}
