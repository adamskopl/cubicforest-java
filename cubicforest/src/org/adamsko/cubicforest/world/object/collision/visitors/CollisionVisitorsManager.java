package org.adamsko.cubicforest.world.object.collision.visitors;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault.TileCollisionType;

/**
 * Manages {@link WorldObject} object's visitors handling collisions. For every
 * collision type, there's a different visitor.
 * 
 * @author adamsko
 */
public interface CollisionVisitorsManager extends Nullable {

	void setVisitingObject(WorldObject visitingObject);

	/**
	 * Get visitor responsible for handling
	 * {@link TileCollisionType#OCCUPANT_ENTERS}
	 */
	WorldObjectVisitor visitEnter();

	/**
	 * Get visitor responsible for handling
	 * {@link TileCollisionType#OCCUPANT_PASSES}
	 */
	WorldObjectVisitor visitPass();

	/**
	 * Get visitor responsible for handling
	 * {@link TileCollisionType#OCCUPANT_LEAVES}
	 */
	WorldObjectVisitor visitLeave();

	/**
	 * Get visitor responsible for handling
	 * {@link TileCollisionType#OCCUPANT_STOPS}
	 */
	WorldObjectVisitor visitStop();
}
