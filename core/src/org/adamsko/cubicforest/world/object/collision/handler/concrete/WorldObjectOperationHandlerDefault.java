package org.adamsko.cubicforest.world.object.collision.handler.concrete;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.object.collision.handler.WorldObjectOperationHandler;

public class WorldObjectOperationHandlerDefault implements
		WorldObjectOperationHandler {

	@Override
	public void remove(final WorldObject worldObject) {
		/**
		 * Set 'DEAD' state, so interested classes will now, that object is no
		 * longer in game. Removing from parent container is done in
		 * CollisionsHandler.
		 */
		worldObject.setState(WorldObjectState.DEAD);
	}

}
