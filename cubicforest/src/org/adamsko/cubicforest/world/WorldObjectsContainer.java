package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class WorldObjectsContainer {

	protected List<WorldObject> worldObjects;
	private TilesMaster tilesMaster;
	
	public WorldObjectsContainer(TilesMaster TM) {
		worldObjects = new ArrayList<WorldObject>();
		tilesMaster = TM;
	}
	
	public void addWorldObject(WorldObject newObject, Vector2 tilePos) {
		worldObjects.add(newObject);
		// associate newObject with a tile
		tilesMaster.insertWorldObject(newObject, tilePos);
	}
}