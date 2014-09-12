package org.adamsko.cubicforest.world.objectsMasters.items.portals;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Interface for portal map objects: object allowing for {@link WorldObject}
 * objects to walk from tile containing portal to another tile containing twin
 * portal.
 * 
 * @author adamsko
 * 
 */
public interface Portal extends WorldObject {

	/**
	 * Get portal's twin portal.
	 */
	Portal getTwinPortal();

	void setTwinPortal(Portal twinPortal);

	/**
	 * Get tile assigned to this portal.
	 */
	Tile getTile();

}
