package org.adamsko.cubicforest.world.object.collision.master;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

/**
 * Handles requests about resolving collisions effects. <br>
 * Additionally exposes operations needed to be performed during resolve.
 * 
 * @author adamsko
 * 
 */
public interface CollisionsMaster {

	void collision(final TileEvent evenType, final Tile collidingTile,
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
	GameResultOperationHandler gameResultOperation();
}
