package org.adamsko.cubicforest.render.queue;

import java.util.Comparator;

import org.adamsko.cubicforest.render.RenderableObject;

import com.badlogic.gdx.math.Vector2;

/**
 * Comparator for {@link RenderableObject}. Used for deciding which
 * object should be rendered first.
 * 
 * Sorting rules order: height, tilePosition(lower coordinates first).
 * @author adamsko
 *
 */
public class RenderableObjectComparator implements Comparator <RenderableObject> {

	@Override
	public int compare(RenderableObject o1, RenderableObject o2) {
		
		if(o1.getHeight() < o2.getHeight()) return -1;			
		if(o1.getHeight() > o2.getHeight()) return 1;
		// objects have equal height
		
		Vector2 o1TP = o1.getTilesPos();
		Vector2 o2TP = o2.getTilesPos();
		if(o1TP.x < o2TP.x) return -1;
		if(o1TP.x > o2TP.x) return 1;
		if(o1TP.y < o2TP.y) return -1;
		if(o1TP.y > o2TP.y) return 1;
				
		return 0;
	}

}
