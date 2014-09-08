package org.adamsko.cubicforest.world;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
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
	 * Return list of all {@link WorldObject} objects in contained by
	 * {@link WorldObjectsMaster}
	 */
	public List<WorldObject> getWorldObjects();

	/**
	 * Based on {@link CFMap} object informations, load objects. Usually: adding
	 * objects to {@link TilesMaster} and to the {@link WorldObjectsMaster}
	 * list.
	 * 
	 * @param cfMap
	 *            {@link CFMap} object containing data about {@link WorldObject}
	 *            objects to be loaded
	 * @throws Exception
	 */
	void loadMapObjects(CFMap cfMap) throws Exception;

	/**
	 * Remove {@link WorldObject} objects to be prepared to
	 * {@link #loadMapObjects(CFMap)}.
	 * 
	 * @throws Exception
	 */
	void unloadMapObjects() throws Exception;

	/**
	 * Initialize {@link CollisionVisitorsManager} for every {@link WorldObject}
	 * object contained by this master.
	 * 
	 * @param collisionVisitorsManagerFactory
	 *            factory object initializing {@link CollisionVisitorsManager}
	 *            by given {@link WorldObjectType}
	 */
	void initCollisionVisitorsManagers(
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

}