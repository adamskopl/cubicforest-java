package org.adamsko.cubicforest.world;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.tile.TilesMaster;

/**
 * Interface for classes managing {@link WorldObjectType} type objects. If there
 * is a new type of world objects, their container should implement this
 * interface.
 * 
 * @author adamsko
 * 
 */
public interface WorldObjectsMaster extends Nullable {
	public void update(float deltaTime);

	/**
	 * Return list of all {@link WorldObject} objects in container
	 * 
	 * @return
	 */
	public List<WorldObject> getWorldObjects();

	/**
	 * Based on {@link CFMap} object informations, load objects. Usually: adding
	 * objects to {@link TilesMaster} and to {@link WorldObject} objects
	 * container or {@link RenderableObject} objects container.
	 * 
	 * @param map
	 * @throws Exception
	 */
	void loadMapObjects(CFMap map) throws Exception;

	/**
	 * Remove {@link WorldObject} objects to be prepared on
	 * {@link #loadMapObjects(CFMap)}.
	 * 
	 * @throws Exception
	 */
	void unloadMapObjects() throws Exception;

	void initCollisionVisitorsManagers(
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

}
