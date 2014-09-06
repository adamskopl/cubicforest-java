package org.adamsko.cubicforest.world.tile.tilesEvents;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.object.collision.handler.concrete.CollisionsHandlerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault.TileCollisionType;

public class TilesEventsHandlerDefault implements TilesEventsHandler {

	private CollisionsHandler collisionsHandler;

	/**
	 * For {@link NullTilesEventsHandler}
	 */
	TilesEventsHandlerDefault() {
	}

	public TilesEventsHandlerDefault(
			final RoundsMaster roundsMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		collisionsHandler = new CollisionsHandlerDefault(roundsMaster);
		collisionVisitorsManagerFactory.setCollisionsHandler(collisionsHandler);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public OrderOperationHandler tileCollision(
			final TileCollisionType evenType, final Tile eventTile,
			final WorldObject eventObject) {

		collisionsHandler.collision(evenType, eventTile, eventObject);

		switch (evenType) {
		case OCCUPANT_ENTERS: {
			eventTile.addOccupant(eventObject);
			break;
		}
		case OCCUPANT_LEAVES: {
			eventTile.removeOccupant(eventObject);
			break;
		}
		default:
		}

		return collisionsHandler.orderOperation();
	}

}
