package org.adamsko.cubicforest.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;

public abstract class WorldObjectsContainer extends RenderableObjectsContainer
		implements WorldObjectsMaster {

	/**
	 * Every container holds one type of objects.
	 */
	private final WorldObjectType type;

	/**
	 * Get type of WorldObject objects from this container.
	 * 
	 * @return
	 */
	@Override
	public WorldObjectType getType() {
		return type;
	}

	private final List<WorldObject> worldObjects;
	private final TilesMaster tilesMaster;

	public WorldObjectsContainer(final String name, final WorldObjectType type,
			final TilesMaster tilesMaster, final String textureName,
			final int tileW, final int tileH) {

		super(name, textureName, tileW, tileH);
		this.type = type;
		this.tilesMaster = tilesMaster;
		worldObjects = new ArrayList<WorldObject>();
	}

	public void removeObject(final WorldObject objectRemove) {
		worldObjects.remove(objectRemove);
		removeRenderableObject(objectRemove);
		tilesMaster.removeWorldObject(objectRemove);
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

	public void handleServantTileEvent(final WorldObject servant,
			final TileEvent tileEvent) {

	}

	protected void removeWorldObjects() throws Exception {
		while (worldObjects.size() != 0) {
			final WorldObject object = worldObjects.get(0);
			removeObject(object);
		}
	}

}