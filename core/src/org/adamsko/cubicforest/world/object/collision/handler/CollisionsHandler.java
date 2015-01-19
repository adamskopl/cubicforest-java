package org.adamsko.cubicforest.world.object.collision.handler;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault.TileCollisionType;

/**
 * Handles requests about resolving collisions effects. <br>
 * Additionally exposes handlers needed to perform operations during resolve.
 * 
 * @author adamsko
 * 
 */
public interface CollisionsHandler extends Nullable {

	void collision(final TileCollisionType evenType, final Tile collidingTile,
			final WorldObject collidingObject);

	/**
	 * Exposing handler connected with orders modifications (e.g. current order
	 * should be paused).
	 */
	OrderOperationHandler orderOperation();

	/**
	 * Exposing handler connected with performing operations on WorldObject
	 * objects like removing them from the game.
	 */
	WorldObjectOperationHandler wordlObjectOperation();

	/**
	 * Exposing handler connected with game result modifications (e.g.
	 * indicating that game is lost).
	 */
	GameResultMaster gameResultOperation();
}
