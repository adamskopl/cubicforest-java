package org.adamsko.cubicforest.render.world.object.tileDirection;

import org.adamsko.cubicforest.world.tile.TileDirection;

/**
 * Changes tile direction of an object: should implement all necessary
 * operations, when object's tile direction is changed.
 */
public interface TileDirectionChanger {

	void changeDirection(TileDirection tileDirection);

	TileDirection getDirection();

}
