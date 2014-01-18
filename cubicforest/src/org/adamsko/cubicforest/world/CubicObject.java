package org.adamsko.cubicforest.world;

import org.adamsko.cubicforest.render.RenderableObject;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class CubicObject extends RenderableObject {
	
	public CubicObject(TextureRegion tr, int texNum) {
		super(tr, texNum);
		tilesPos = new Vector2(0, 0);
	}
	
}
