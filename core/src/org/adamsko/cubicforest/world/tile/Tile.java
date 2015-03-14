package org.adamsko.cubicforest.world.tile;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.object.RenderableObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.Portal;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;

import com.badlogic.gdx.math.Vector2;

/**
 * A tile which is a base indicator of all {@link WorldObject} objects positions
 * in the world. Base element of every level. Controls the movement of objects,
 * indicates when they are colliding. Element of calculated paths.
 * 
 * @author adamsko
 */
public interface Tile extends WorldObject, RenderableObject, Nullable {

	/**
	 * Get all {@link WorldObject} objects occupying this tile.
	 */
	List<WorldObject> getOccupants();

	/**
	 * Check if tile is occupied by given object.
	 */
	boolean isOccupied(final WorldObject object);

	/**
	 * Add new {@link WorldObject} object to this tile. From now on this object
	 * is associated with this tile and 'tile driven' events will be also
	 * associated with this object (e.g. collisions).
	 */
	void addOccupant(final WorldObject insertObject);

	/**
	 * Add new {@link Portal} object to this tile. From now on this objects is
	 * associated with this tile.
	 * 
	 * @param portal
	 */
	void addPortal(Portal portal);

	/**
	 * Check if vector belongs to tile's area.
	 * 
	 * @param tilePos
	 *            vector being checked for inclusion
	 */
	boolean isPosInTile(final Vector2 tilePos);

	/**
	 * Check if vector belongs to tile's area.
	 * 
	 * @param tilePos
	 *            vector being checked for inclusion
	 */
	boolean isPosInTile(final float x, final float y);

	/**
	 * Removes given occupant from 'occupants' collection. Removed occupant is
	 * no longer connected with this tile. If the passed object is not on the
	 * tile, it's considered as an error.
	 */
	void removeOccupant(final WorldObject occupantToRemove);

	/**
	 * Remove all occupants that have {@link WorldObjectState#DEAD} state.
	 */
	void removeDeadOccupants();

	/**
	 * Is tile valid for search algorithms (can it be included in the tile
	 * paths)? If at least one occupant is not valid for search algorithms, the
	 * whole tile is not valid.
	 * 
	 * @return yes, if this tile should be considered by search algorithms. e.g
	 *         tile with an impassable {@link WorldObject} object is excluded by
	 *         search algorithms
	 */
	boolean isTilePathSearchValid();

	/**
	 * Indicate if tile is compatible with given {@link TilesSearchParameter}.
	 * Decision is based on occupants {@link TilePropertiesIndicator}.
	 * 
	 * @return yes, if tile should be considered in search method parameterized
	 *         by {@link TilesSearchParameter}
	 */
	boolean isTileValidSearchParameter(TilesSearchParameter tilesSearchParameter);

	/**
	 * @return
	 */
	boolean hasPortal();

	Portal getPortal();

}
