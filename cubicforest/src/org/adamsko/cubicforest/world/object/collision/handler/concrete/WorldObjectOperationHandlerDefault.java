package org.adamsko.cubicforest.world.object.collision.handler.concrete;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.object.collision.handler.WorldObjectOperationHandler;

import com.badlogic.gdx.Gdx;

public class WorldObjectOperationHandlerDefault implements
		WorldObjectOperationHandler {

	@Override
	public void remove(final WorldObject worldObject) {
		Gdx.app.debug("Handler remove()", worldObject.getName());
		// set 'DEAD' state, so interested container classes will now, that
		// object should be removed
		worldObject.setState(WorldObjectState.DEAD);
		// worldObject.getParentContainer().removeObject(worldObject);
	}

}
