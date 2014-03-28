package org.adamsko.cubicforest.world;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public interface WorldObjectsMaster {
	public void update(float deltaTime);
	public List<WorldObject> getWorldObjects();

	/**
	 * TODO: desc
	 * 
	 * @param servant
	 * @param tileEvent
	 */
	public void handleServantTileEvent(WorldObject servant,
			TileEvent_e tileEvent);
	
}
