package org.adamsko.cubicforest.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMemento;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoDefault;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoState;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoStateDefault;
import org.adamsko.cubicforest.render.world.RenderableObjectsMasterDefault;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.Portal;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;

public abstract class WorldObjectsMasterDefault extends
		RenderableObjectsMasterDefault implements WorldObjectsMaster {

	private List<WorldObject> worldObjects;
	private TilesMaster tilesMaster;

	public WorldObjectsMasterDefault(final int nullConstructor) {
		super(0);
	}

	public WorldObjectsMasterDefault(final String name,
			final TilesMaster tilesMaster, final String textureName,
			final int tileW, final int tileH) {

		super(name, textureName, tileW, tileH);
		this.tilesMaster = tilesMaster;
		worldObjects = new ArrayList<WorldObject>();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	/**
	 * Get {@link WorldObject} objects.
	 * 
	 * @return {@link WorldObject} objects list.
	 */
	@Override
	public List<WorldObject> getWorldObjects() {
		return worldObjects;
	}

	@Override
	public void initCollisionVisitorsManagers(
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {
		if (collisionVisitorsManagerFactory.isNull()) {
			Gdx.app.error(
					"WorldObjectsContainer::initCollisionVisitorsManagers()",
					"collisionVisitorsManagerFactory.isNull()");
			return;
		}
		for (final WorldObject worldObject : getWorldObjects()) {
			worldObject
					.initCollisionVisitorsManager(collisionVisitorsManagerFactory);
		}
	}

	@Override
	public WOMMemento createMemento() {
		final WOMMementoState state = new WOMMementoStateDefault(this);
		final WOMMemento memento = new WOMMementoDefault();
		memento.setState(state);
		return memento;
	}

	@Override
	public void setMemento(final WOMMemento memento) {
		unloadMapObjects();
		final WOMMementoState state = memento.getState();
		loadMapObjects(state.getTilePositions());
	}

	public void removeObjectFromContainer(final WorldObject objectRemove) {
		worldObjects.remove(objectRemove);
		removeRenderableObject(objectRemove);
	}

	public void removeObjectFromTile(final WorldObject objectRemove) {
		tilesMaster.removeWorldObject(objectRemove);
	}

	protected void removeWorldObjects() {
		while (worldObjects.size() != 0) {
			final WorldObject object = worldObjects.get(0);
			removeObjectFromContainer(object);
			removeObjectFromTile(object);
		}
	}

	public void addObject(final Portal portal) {
		worldObjects.add(portal);
		addRenderableObject(portal);
		// associate newObject with a tile (every WorldObject is associated with
		// a tile)
		tilesMaster.addWorldObject(portal);
	}

	@Override
	public void addObject(final WorldObject worldObject) {
		worldObjects.add(worldObject);
		addRenderableObject(worldObject);
		// associate newObject with a tile (every WorldObject is associated with
		// a tile)
		tilesMaster.addWorldObject(worldObject);
	}

	public boolean containsObject(final WorldObject object) {
		return worldObjects.contains(object);
	}

}