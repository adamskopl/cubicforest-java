package org.adamsko.cubicforest.world.tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * A tile which is a base indicator of all objects positions in the world.
 * 
 * @author adamsko
 */
public class Tile extends WorldObject {

	public static boolean occupantsRefactor = true;

	private final List<WorldObject> occupants;

	private WorldObject occupant;
	/**
	 * For now: item created by Heroes and GatherCubes
	 */
	private WorldObject item;

	/**
	 * Does tile contain a {@link WorldObject} object?
	 */
	private Boolean hasOccupant;
	/**
	 * 
	 */
	private Boolean hasItem;

	/**
	 * For NullTile constructor.
	 */
	Tile() {
		super();
		occupants = null;
	}

	public Tile(final Vector2 coords, final TextureRegion tr,
			final WorldObjectsContainer container) {
		super(tr, 0, container, WorldObjectType.OBJECT_UNDEFINED,
				WorldObjectType.OBJECT_UNDEFINED);
		this.tilesPos = coords;

		occupants = new ArrayList<WorldObject>();

		occupant = null;
		hasOccupant = false;

		item = null;
		hasItem = false;
	}

	/**
	 * Check if vector belongs to tile's area.
	 * 
	 * @param tilePos
	 *            vector being checked for inclusion
	 * @return
	 */
	public boolean isPosInTile(final Vector2 tilePos) {

		if (tilePos.x >= tilesPos.x && tilePos.x < tilesPos.x + 1) {
			if (tilePos.y >= tilesPos.y && tilePos.y < tilesPos.y + 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if tile is occupied by given object.
	 * 
	 * @param object
	 * @return
	 */
	public boolean isOccupied(final WorldObject object) {
		return occupants.contains(object);
	}

	/**
	 * Get {@link WorldObject} object associated with this tile.
	 * 
	 * @return {@link WorldObject} object associated with this tile.
	 */
	public WorldObject getOccupant() {
		return occupant;
	}

	public List<WorldObject> getOccupants() {
		return occupants;
	}

	public WorldObject getItem() {
		return item;
	}

	public void addOccupant(final WorldObject insertObject,
			final boolean ignoreOccupation) throws Exception {

		if (Tile.occupantsRefactor) {
			occupants.add(insertObject);
		} else {

			switch (insertObject.getType()) {
			case OBJECT_ENTITY:
				if (!ignoreOccupation && hasOccupant()) {
					throw new Exception(
							"insertWorldObject: tile is already occupied");
				}
				occupant = insertObject;
				hasOccupant = true;

				break;
			case OBJECT_ITEM:
				if (!ignoreOccupation && hasItem()) {
					throw new Exception("insertItem: tile has already an item");
				}
				item = insertObject;
				hasItem = true;
				break;
			case OBJECT_TERRAIN:
				occupant = insertObject;
				hasOccupant = true;
				break;
			default:
				Gdx.app.error("Tile::insertObject()", "worldType unsupported");
				break;
			}

		}
	}

	/**
	 * Removes given occupant from 'occupants' collection. Removed occupant is
	 * no longer connected with this tile.
	 * 
	 * @param removedOccupant
	 */
	public void removeOccupant(final WorldObject removedOccupant) {
		if (!occupants.remove(removedOccupant)) {
			// if there was no 'removedOccupant' object in a collection
			Gdx.app.error("Tile::removeOccupant()",
					"no " + removedOccupant.getName() + " in 'occupants'");
		}
	}

	public void removeDeadOccupants() {
		final Iterator<WorldObject> iter = getOccupants().iterator();
		while (iter.hasNext()) {
			final WorldObject tileObject = iter.next();
			if (tileObject.getState() == WorldObjectState.DEAD) {
				tileObject.getParentContainer().removeObjectFromContainer(
						tileObject);
				iter.remove();
			}
		}
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public WorldObject occupantLeaves() throws Exception {
		if (!hasOccupant()) {
			throw new Exception("tile not occupied");
		}
		final WorldObject takenObject = occupant;
		occupant = null;
		hasOccupant = false;
		return takenObject;
	}

	public WorldObject itemLeaves() throws Exception {
		if (!hasItem()) {
			throw new Exception("tile without item");
		}
		final WorldObject takenItem = item;
		item = null;
		hasItem = false;
		return takenItem;
	}

	/**
	 * Does tile have an occupant?
	 * 
	 * @return
	 */
	public Boolean hasOccupant() {
		return hasOccupant;
	}

	/**
	 * Does tile have any occupant?
	 * 
	 * @return
	 */
	public Boolean hasOccupant2() {
		return !occupants.isEmpty();
	}

	/**
	 * Does tile have an item?
	 * 
	 * @return
	 */
	public Boolean hasItem() {
		return hasItem;
	}

	/**
	 * Is tile passable? Could it be occupied/passed?
	 * 
	 * @return
	 */
	public Boolean isPassable() {
		if (Tile.occupantsRefactor) {
			return !hasOccupant2();
		} else {
			return !hasOccupant();
		}
	}

	/**
	 * Is tile valid for search algorithms (if it can be included in tile
	 * paths)? If at least one occupant is not valid for search algorithms, the
	 * whole tile is not.
	 * 
	 * @return
	 */
	public boolean getTilePathSearchValid() {
		for (final WorldObject occupant : getOccupants()) {
			if (!occupant.getTilePropertiesIndicator().getTilePathSearchValid()) {
				// one of the occupants is not valid - Tile is not valid
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return getTilesPos().toString();
	}

}