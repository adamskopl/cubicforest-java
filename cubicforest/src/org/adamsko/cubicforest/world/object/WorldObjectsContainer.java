package org.adamsko.cubicforest.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
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
		super(TM, worldObjectsType, textureName, tileW, tileH); 
		worldObjects = new ArrayList<WorldObject>();
		tilesMaster = TM;
		this.worldObjectsType = worldObjectsType;
	}
	
	public void removeWorldObject(WorldObject objectRemove) {
		worldObjects.remove(objectRemove);
		Gdx.app.error("removeWorldObject: ", Integer.toString(worldObjects.size()));
		
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// TODO: REMOVE OBJECT FROM TILE !!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
	}
	
	public void addWorldObject(WorldObject newObject) {
		worldObjects.add(newObject);

		addRenderableObject(newObject);
		
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