package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObjectsContainer;

public class WorldObjectsContainer {

	protected List<WorldObject> worldObjects;
	
	public WorldObjectsContainer() {
		worldObjects = new ArrayList<WorldObject>();
	}	
}