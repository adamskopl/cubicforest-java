package org.adamsko.cubicforest.world.tile.tilesEvents;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.object.collision.handler.concrete.CollisionsHandlerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;

public class TilesEventsHandler {

	private final TilesContainer tilesContainer;
	private final CollisionsHandler collisionsHandler;

	public TilesEventsHandler(final TilesContainer tilesContainer,
			final RoundsMaster roundsMaster) {
		this.tilesContainer = tilesContainer;

		collisionsHandler = new CollisionsHandlerDefault(roundsMaster);

		CollisionVisitorsManagerFactory.instance().setCollisionsHandler(
				collisionsHandler);
	}

	/**
	 * Handle an event: {@link Tile} + {@link WorldObject}
	 * 
	 * @param evenType
	 * @param eventTile
	 * @param eventObject
	 * @throws Exception
	 */
	public OrderOperationHandler tileEvent(final TileEvent evenType,
			final Tile eventTile, final WorldObject eventObject)
			throws Exception {

		collisionsHandler.collision(evenType, eventTile, eventObject);

		switch (evenType) {
		case OCCUPANT_ENTERS: {
			eventTile.addOccupant(eventObject, true);
			tilesContainer.testHighlightTile(eventTile, 0, 1);
			break;
		}
		case OCCUPANT_LEAVES: {
			eventTile.removeOccupant(eventObject);
			tilesContainer.testHighlightTile(eventTile, 0, 0);
			break;
		}
		default:
		}

		return collisionsHandler.orderOperation();
	}

}
