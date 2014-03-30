package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;


public class WorldObjectsContainer {

	private List<WorldObject> worldObjects;
	private TilesMaster tilesMaster;
	/**
	 * Every container contains only one type of objects.
	 */
	private WorldObjectType_e worldObjectsType;
	
	public WorldObjectType_e getWorldObjectsType() {
		return worldObjectsType;
	}

	public WorldObjectsContainer(TilesMaster TM, WorldObjectType_e worldObjectsType) {
		worldObjects = new ArrayList<WorldObject>();
		tilesMaster = TM;
		this.worldObjectsType = worldObjectsType;
	}
	
	public void addWorldObject(WorldObject newObject, WorldObjectsMaster hisMaster) {
		worldObjects.add(newObject);
		newObject.setMaster(hisMaster);
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
	
	public void handleServantTileEvent(WorldObject servant,
			TileEvent_e tileEvent) {
		
	}

}