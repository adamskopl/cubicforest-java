package org.adamsko.cubicforest.world;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;

public interface WorldObjectsMaster extends RenderableObjectsMaster {
	public void update(float deltaTime);

	public List<WorldObject> getWorldObjects();

	void loadMapObjects(CFMap map) throws Exception;

	void unloadMapObjects() throws Exception;

	WorldObjectType getType();
}
