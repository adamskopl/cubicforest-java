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
	 * Vertical position of the object.
	 */
	protected float height;
	
	public Vector2 getTilesPos() {
		return tilesPos;
	}
	
	public float getHeight() {
		return height;
	}
	
}