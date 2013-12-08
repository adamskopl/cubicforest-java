package org.adamsko.cubicforest.world;

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
	
}