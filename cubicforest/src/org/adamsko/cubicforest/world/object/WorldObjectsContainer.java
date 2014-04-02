package org.adamsko.cubicforest.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;


public class WorldObjectsContainer extends RenderableObjectsContainer {

	private List<WorldObject> worldObjects;
	private TilesMaster tilesMaster;
	/**
	 * Every container contains only one type of objects.
	 */
	private WorldObjectType_e worldObjectsType;
	
	public WorldObjectType_e getWorldObjectsType() {
		return worldObjectsType;
	}

	public WorldObjectsContainer(TilesMaster TM, WorldObjectType_e worldObjectsType, String textureName,
			int tileW, int tileH) {
		super(TM, textureName, tileW, tileH); 
		worldObjects = new ArrayList<WorldObject>();
		tilesMaster = TM;
		this.worldObjectsType = worldObjectsType;
	}
	
	public void removeObject(WorldObject objectRemove) throws Exception {
		worldObjects.remove(objectRemove);
		removeRenderableObject(objectRemove);
		tilesMaster.removeWorldObject(objectRemove);		
	}
	
	public void addObject(WorldObject worldObject) {
		worldObjects.add(worldObject);
		addRenderableObject(worldObject);
		// associate newObject with a tile (every WorldObject is associated with
		// a tile)
		tilesMaster.addWorldObject(worldObject);
	}
	
//	public void addWorldObject(WorldObject newObject) {
//		worldObjects.add(newObject);
//
//		addRenderableObject(newObject);
//		
//		// associate newObject with a tile (every WorldObject is associated with
//		// a tile)
//		tilesMaster.addWorldObject(newObject);
//	}
	
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