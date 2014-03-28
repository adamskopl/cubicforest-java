package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;


public class WorldObjectsContainer {

	private List<WorldObject> worldObjects;
	private TilesMaster tilesMaster;
	
	public WorldObjectsContainer(TilesMaster TM) {
		worldObjects = new ArrayList<WorldObject>();
		tilesMaster = TM;
	}
	
	public void addWorldObject(WorldObject newObject, WorldObjectsMaster hisMaster) {
		worldObjects.add(newObject);
		newObject.setMaster(hisMaster);
		// associate newObject with a tile (every WorldObject is associated with
		// a tile)
		if(newObject.isOccupyingTile()) {
			tilesMaster.insertWorldObject(newObject);
		}
	}
	
	/**
	 * Get {@link WorldObject} objects.
	 * 
	 * @return {@link WorldObject} objects list.
	 */
	public List<WorldObject> getWorldObjects() {
		return worldObjects;
	}
	
	public void handleServantTileEvent(WorldObject servant,
			TileEvent_e tileEvent) {
		
	}
}