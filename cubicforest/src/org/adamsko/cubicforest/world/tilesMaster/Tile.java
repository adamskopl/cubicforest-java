package org.adamsko.cubicforest.world.tilesMaster;

import java.util.List;

import org.adamsko.cubicforest.world.object.Type;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * A tile which is a base indicator of all objects positions in the world.
 * 
 * @author adamsko
 */
public class Tile extends WorldObject {

	private List<WorldObject> occupants;

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

	public Tile(final Vector2 coords, final TextureRegion tr) {
		super(tr, 0, Type.OBJECT_UNDEFINED);
		this.tilesPos = coords;

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
	 * Get {@link WorldObject} object associated with this tile.
	 * 
	 * @return {@link WorldObject} object associated with this tile.
	 */
	public WorldObject getOccupant() {
		return occupant;
	}

	public WorldObject getItem() {
		return item;
	}

	public void insertObject(final WorldObject insertObject,
			final boolean ignoreOccupation) throws Exception {

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
		return !hasOccupant();
	}

	@Override
	public String toString() {
		return getTilesPos().toString();
	}

}