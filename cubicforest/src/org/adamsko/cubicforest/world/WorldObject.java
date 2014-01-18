package org.adamsko.cubicforest.world;

import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 * 
 */
public class WorldObject {

	/* 
	 * Position indicated by tiles. 
	 * (0.0,0.0): uppper corner of the first tile.
	 * (2.5, 1.5): center of the field with 
	 * (2, 1) coordinates 
	 */
	protected Vector2 tilesPos;
	
	/*
	 * Vertical position of the object. Indicates order of the rendering.
	 */
	protected Float height;
	
	/**
	 * {@link WorldObjectsMaster} which is managing this object.
	 */
	private WorldObjectsMaster master = null;
	
	public WorldObject() {
		tilesPos = new Vector2(0.0f, 0.0f);
		height = new Float(0.0f);
	}
	
	/**
	 * Set {@link WorldObjectsMaster} which is managing this object.
	 * 
	 * @param master {@link WorldObjectsMaster} of this object
	 */
	public void setMaster(WorldObjectsMaster master) {
		this.master = master;
	}
	
	public void setTilesPos(Vector2 pos) {
		this.tilesPos.set(pos);
	}
	
	public Vector2 getTilesPos() {
		return this.tilesPos;
	}
	
	public void setHeight(Float height) {
		this.height = height;
	}
	
	public float getHeight() {
		return height;
	}
	
	/**
	 * Handle {@link TileEvent_e} event of a parent {@link Tile}.
	 * 
	 * @param tileEvent
	 *            event associated with a parent {@link Tile}.
	 */
	public void handleParentTileEvent(TileEvent_e tileEvent) {
		master.handleServantTileEvent(this, tileEvent);
	}
	
}