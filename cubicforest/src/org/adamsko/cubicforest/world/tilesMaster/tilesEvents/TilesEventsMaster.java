package org.adamsko.cubicforest.world.tilesMaster.tilesEvents;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
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
	public OrderOperation_e tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		switch (evenType) {
		case OCCUPANT_ENTERS: {
			eventTile.insertObject(eventObject);
			tilesContainer.testHighlightTile(eventTile, 0, 1);
			break;
		}
		case OCCUPANT_LEAVES: {
			eventTile.occupantLeaves();
			tilesContainer.testHighlightTile(eventTile, 0, 0);
			break;
		}
		case OCCUPANT_STOPS: {
			break;
		}
		case OCCUPANT_PASSES: {
			break;
		}
		default: {
			throw new Exception("tileEvent: unsupported event type");
		}
		}
		
		return interactionMaster.tileEvent(evenType, eventTile, eventObject);
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

			break;
		}
		default: {
			throw new Exception("tileEvent: unsupported event type");
		}
		}

	}

}
