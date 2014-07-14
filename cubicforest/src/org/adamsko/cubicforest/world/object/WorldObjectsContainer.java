package org.adamsko.cubicforest.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class WorldObjectsContainer extends RenderableObjectsContainer {

	private List<WorldObject> worldObjects;
	private TilesMaster tilesMaster;
	/**
	 * Every container contains only one type of objects.
	 */
	private WorldObjectType worldObjectsType;

	public WorldObjectType getWorldObjectsType() {
		return worldObjectsType;
	}

	public WorldObjectsContainer(String name, MapsLoader mapsLoader,
			TilesMaster TM, WorldObjectType worldObjectsType,
			String textureName, int tileW, int tileH) {
		super(name, TM, textureName, tileW, tileH);
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

	public boolean containsObject(WorldObject object) {
		return worldObjects.contains(object);
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
			TileEvent tileEvent) {

	}

	protected void removeWorldObjects() throws Exception {
		while (worldObjects.size() != 0) {
			WorldObject object = worldObjects.get(0);
			removeObject(object);
		}
	}

}