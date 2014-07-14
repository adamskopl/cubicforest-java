package org.adamsko.cubicforest.world;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.WorldObject;

public interface WorldObjectsMaster extends RenderableObjectsMaster {
	public void update(float deltaTime);
	public List<WorldObject> getWorldObjects();
	void loadMapObjects(MapsLoader mapsLoader) throws Exception;
	void unloadMapObjects(MapsLoader mapsLoader) throws Exception;
}
