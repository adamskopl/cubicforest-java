package org.adamsko.cubicforest.world.tilesMaster;

import org.adamsko.cubicforest.world.CubicObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * A tile which is a base indicator of all objects positions in the world.
 * @author adamsko
 *
 */
public class Tile extends CubicObject {
		
	public Tile(Vector2 coords, TextureRegion tr) {
		super(tr);
		this.worldPos = coords;
		
		Vector2 rendVec = new Vector2(-tr.getRegionWidth()/2, -tr.getRegionHeight());					
		setRenderVector(rendVec);
	}
	
	/**
	 * Check if vector belongs to tile's area.
	 * @param tilePos vector being checked for inclusion
	 * @return
	 */
	public boolean isPosInTile(Vector2 tilePos) {
		
		if (tilePos.x > worldPos.x && tilePos.x < worldPos.x+1) 				
			if(tilePos.y > worldPos.y && tilePos.y < worldPos.y+1) {
				return true;	
		}
		return false;
	}
	
}