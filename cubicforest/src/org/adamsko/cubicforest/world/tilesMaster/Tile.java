package org.adamsko.cubicforest.world.tilesMaster;

import org.adamsko.cubicforest.world.CubicObject;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * A tile which is a base indicator of all objects positions in the world.
 * 
 * @author adamsko
 */
public class Tile extends CubicObject {
		
	private WorldObject occupant;
	private Boolean occupied;
	
	public Tile(Vector2 coords, TextureRegion tr) {
		super(tr, 0);
		this.tilesPos = coords;
		occupant = null;
		occupied = false;
	}
	
	/**
	 * Check if vector belongs to tile's area.
	 * 
	 * @param tilePos
	 *            vector being checked for inclusion
	 * @return
	 */
	public boolean isPosInTile(Vector2 tilePos) {
		
		if (tilePos.x > tilesPos.x && tilePos.x < tilesPos.x+1) 				
			if(tilePos.y > tilesPos.y && tilePos.y < tilesPos.y+1) {
				return true;	
		}
		return false;
	}
	
	/**
	 * @param insertObject
	 *            WorldObject to be inserted into the tile
	 */
	public void insertWorldObject(WorldObject insertObject) {
		occupant = insertObject;
		occupied = true;
	}
	
	public Boolean isTileOccupied() {
		return occupied;
	}

	/**
	 * Handle {@link TileEvent_e} event associated with this {@link Tile}.
	 * 
	 * @param tileEvent
	 *            {@link TileEvent_e} event associated with this {@link Tile}.
	 */
	public void handleTileEvent(TileEvent_e tileEvent) {
		if (isTileOccupied()) {
			occupant.handleParentTileEvent(tileEvent);
		}
	}
	
}