package org.adamsko.cubicforest.world.object.collision.visitors;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;

/**
 * Manages {@link WorldObject} object's visitors.
 * 
 * @author adamsko
 * 
 */
public interface CollisionVisitorsManager {

	void setVisitingObject(WorldObject visitingObject);

	WorldObjectVisitor visitEnter();

	WorldObjectVisitor visitPass();

	WorldObjectVisitor visitLeave();

	WorldObjectVisitor visitStop();
}
