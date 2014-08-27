package org.adamsko.cubicforest.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;

public abstract class WorldObjectsContainer extends RenderableObjectsContainer
		implements WorldObjectsMaster {

	private final List<WorldObject> worldObjects;
	private final TilesMaster tilesMaster;

	public WorldObjectsContainer(final String name,
			final TilesMaster tilesMaster, final String textureName,
			final int tileW, final int tileH) {

		super(name, textureName, tileW, tileH);
		this.tilesMaster = tilesMaster;
		worldObjects = new ArrayList<WorldObject>();
	}

	public void removeObjectFromContainer(final WorldObject objectRemove) {
		worldObjects.remove(objectRemove);
		removeRenderableObject(objectRemove);
	}

	public void removeObjectFromTile(final WorldObject objectRemove) {
		tilesMaster.removeWorldObject(objectRemove);
	}

	protected void removeWorldObjects() throws Exception {
		while (worldObjects.size() != 0) {
			final WorldObject object = worldObjects.get(0);
			removeObjectFromContainer(object);
			removeObjectFromTile(object);
		}
	}

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

	/**
	 * Get {@link WorldObject} objects.
	 * 
	 * @return {@link WorldObject} objects list.
	 */
	@Override
	public List<WorldObject> getWorldObjects() {
		return worldObjects;
	}
	//
	// public void handleServantTileEvent(final WorldObject servant,
	// final TileEvent tileEvent) {
	//
	// }

}