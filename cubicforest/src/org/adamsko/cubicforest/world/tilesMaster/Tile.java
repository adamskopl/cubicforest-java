package org.adamsko.cubicforest.world.tilesMaster;

import java.util.List;

import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * A tile which is a base indicator of all objects positions in the world.
 * 
 * @author adamsko
 */
public class Tile extends RenderableObject {

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

	public Tile(Vector2 coords, TextureRegion tr) {
		super(tr, 0, WorldObjectType_e.OBJECT_TILE);
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
	public boolean isPosInTile(Vector2 tilePos) {

		if (tilePos.x >= tilesPos.x && tilePos.x < tilesPos.x + 1)
			if (tilePos.y >= tilesPos.y && tilePos.y < tilesPos.y + 1) {
				return true;
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

	/**
	 * @param insertObject
	 *            WorldObject to be inserted into the tile
	 * @throws Exception
	 */
	public void insertObject(WorldObject insertObject) throws Exception {
		if (hasOccupant()) {
			throw new Exception("insertWorldObject: tile is already occupied");
		}
		occupant = insertObject;
		hasOccupant = true;
	}

	public void insertItem(WorldObject insertItem) throws Exception {
		if (hasItem()) {
			throw new Exception("insertItem: tile has already an item");
		}
		item = insertItem;
		hasItem = true;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public WorldObject occupantLeaves() throws Exception {
		if (!hasOccupant()) {
			throw new Exception("tile not occupied");
		}
		WorldObject takenObject = occupant;
		occupant = null;
		hasOccupant = false;
		return takenObject;
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

	/**
	 * Handle {@link TileEvent_e} event associated with this {@link Tile}.
	 * 
	 * @param tileEvent
	 *            {@link TileEvent_e} event associated with this {@link Tile}.
	 */
	public void handleTileEvent(TileEvent_e tileEvent) {
		if (hasOccupant()) {
			occupant.handleParentTileEvent(tileEvent);
		}
	}

	public String toString() {
		return getTilesPos().toString();
	}

}