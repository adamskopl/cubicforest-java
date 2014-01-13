package org.adamsko.cubicforest.world;

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
	
	public WorldObject() {
		tilesPos = new Vector2(0.0f, 0.0f);
		height = new Float(0.0f);
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
	
}