package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;


public class WorldObjectsContainer {

	private List<WorldObject> worldObjects;
	private TilesMaster tilesMaster;
	
	public WorldObjectsContainer(TilesMaster TM) {
		worldObjects = new ArrayList<WorldObject>();
		tilesMaster = TM;
	}
	
	public void addWorldObject(WorldObject newObject) {
		worldObjects.add(newObject);
		// associate newObject with a tile (every WorldObject is associated with
		// a tile)
		tilesMaster.insertWorldObject(newObject);
	}
	
	/**
	 * Get {@link WorldObject} objects.
	 * 
	 * @return {@link WorldObject} objects list.
	 */
	public List<WorldObject> getWorldObjects() {
		return worldObjects;
	}
}