package org.adamsko.cubicforest.world.objectsMasters;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMemento;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

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

	void addObject(final WorldObject worldObject);

	void removeObjectFromContainer(final WorldObject objectRemove);

	public String getName();

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
	 * Based on tiles positions, load objects. <br>
	 * Created for {@link #setMemento(WOMMemento)}. The assumption is, that
	 * effect of loading should be the same as with
	 * {@link #loadMapObjects(CFMap)!
	 * 
	 * @param tilePositions tile positions of loaded object
	 */
	void loadMapObjects(List<Vector2> tilePositions);

	/**
	 * Remove {@link WorldObject} objects to be prepared to
	 * {@link #loadMapObjects(CFMap)}.
	 * 
	 * @throws Exception
	 */
	void unloadMapObjects();

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

	void setMemento(WOMMemento memento);

	WOMMemento createMemento();

	/**
	 * Design flaw: this should be only in {@link RenderableObjectsMaster}
	 */
	void changeTexture(final RenderableObject object,
			final Vector2 textureCoordinates);

	/**
	 * Design flaw: this should be only in {@link RenderableObjectsMaster}
	 */
	void changeTexture(final RenderableObject object, final int atlasRow,
			final int atlasCol);

}
