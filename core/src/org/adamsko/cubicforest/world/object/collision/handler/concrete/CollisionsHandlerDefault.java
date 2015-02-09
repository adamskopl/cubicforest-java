package org.adamsko.cubicforest.world.object.collision.handler.concrete;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.object.collision.handler.WorldObjectOperationHandler;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault.TileCollisionType;

public class CollisionsHandlerDefault implements CollisionsHandler {

	OrderOperationHandler orderOperationHandler;
	WorldObjectOperationHandler worldObjectOperationHandler;
	GameResultMaster gameResultMaster;

	/**
	 * For NullCollisionsHandlerDefault
	 */
	CollisionsHandlerDefault() {
	}

	public CollisionsHandlerDefault(final RoundsMaster roundsMaster) {
		this.orderOperationHandler = new OrderOperationHandlerDefault();
		this.worldObjectOperationHandler = new WorldObjectOperationHandlerDefault();
		this.gameResultMaster = roundsMaster.getGameResultMaster();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void collision(final TileCollisionType evenType,
			final Tile collidingTile, final WorldObject collidingObject) {

		orderOperationHandler.reset();

		for (final WorldObject o : collidingTile.getOccupants()) {
			switch (evenType) {
			case OCCUPANT_ENTERS:
				o.accept(collidingObject.collision().visitEnter());
				break;

			case OCCUPANT_PASSES:
				o.accept(collidingObject.collision().visitPass());
				break;

			case OCCUPANT_LEAVES:
				o.accept(collidingObject.collision().visitLeave());
				break;

			case OCCUPANT_STOPS:
				o.accept(collidingObject.collision().visitStop());
			default:
				break;
			}
		}
		removeDeadObjects(collidingTile);
	}

	@Override
	public OrderOperationHandler orderOperation() {
		return orderOperationHandler;
	}

	@Override
	public WorldObjectOperationHandler wordlObjectOperation() {
		return worldObjectOperationHandler;
	}

	@Override
	public GameResultMaster gameResultOperation() {
		return gameResultMaster;
	}

	private void removeDeadObjects(final Tile collidingTile) {
		collidingTile.removeDeadOccupants();
	}
}
