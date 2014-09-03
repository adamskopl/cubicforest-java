package org.adamsko.cubicforest.world.tile;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;

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
	 * 
	 * @return
	 */
	List<WorldObject> getOccupants();

	/**
	 * Check if tile is occupied by given object.
	 */
	boolean isOccupied(final WorldObject object);

	/**
	 * Does tile have any occupant?
	 */
	boolean hasOccupant();

	/**
	 * Add new {@link WorldObject} object to this tile. From now on this object
	 * is associated with this tile and 'tile driven' events will be also
	 * associated with this object (e.g. collisions).
	 */
	void addOccupant(final WorldObject insertObject) throws Exception;

	/**
	 * Invoked when tile's state has changed. E.g. object has left tile, so
	 * maybe it should change the texture.
	 */
	void refresh();

	/**
	 * Check if vector belongs to tile's area.
	 * 
	 * @param tilePos
	 *            vector being checked for inclusion
	 * @return
	 */
	boolean isPosInTile(final Vector2 tilePos);

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

}
