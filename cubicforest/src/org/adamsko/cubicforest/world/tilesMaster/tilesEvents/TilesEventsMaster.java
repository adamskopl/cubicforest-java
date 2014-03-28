package org.adamsko.cubicforest.world.tilesMaster.tilesEvents;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public class TilesEventsMaster {

	private TilesContainer tilesContainer;
	private InteractionMaster interactionMaster;

	public TilesEventsMaster(TilesContainer tilesContainer) {
		this.tilesContainer = tilesContainer;
	}

	public void setInteractionMaster(InteractionMaster interactionMaster) {
		this.interactionMaster = interactionMaster;
	}

	/**
	 * Handle an event: {@link Tile} + {@link WorldObject}
	 * 
	 * @param evenType
	 * @param eventTile
	 * @param eventObject
	 * @throws Exception
	 */
	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		switch (evenType) {
		case OCCUPANT_ENTERS: {
			eventTile.insertWorldObject(eventObject);
			tilesContainer.testHighlightTile(eventTile, 1);
			interactionMaster.tileEvent(evenType, eventTile, eventObject);
			break;
		}
		case OCCUPANT_LEAVES: {
			break;
		}
		default: {
			throw new Exception("tileEvent: unsupported event type");
		}
		}
	}

	/**
	 * @param evenType
	 * @param eventTile
	 * @throws Exception
	 */
	public void tileEvent(TileEvent_e evenType, Tile eventTile)
			throws Exception {

		switch (evenType) {
		case OCCUPANT_ENTERS: {

			break;
		}
		case OCCUPANT_LEAVES: {
			eventTile.occupantLeaves();
			tilesContainer.testHighlightTile(eventTile, 0);
			break;
		}
		default: {
			throw new Exception("tileEvent: unsupported event type");
		}
		}
	}

}
