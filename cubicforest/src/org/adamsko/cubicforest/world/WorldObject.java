package org.adamsko.cubicforest.world;

import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 *
 */
public class WorldObject {

	/* 
	 * World position indicated by tiles. 
	 * (0.0,0.0): uppper corner of the first tile.
	 * (2.5, 1.5): center of the field with 
	 * (2, 1) coordinates 
	 */
	protected Vector2 worldPos;
	
	public Vector2 getWorldPos() {
		return worldPos;
	}
	
}