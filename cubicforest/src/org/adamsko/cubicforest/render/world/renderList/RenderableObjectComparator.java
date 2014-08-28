package org.adamsko.cubicforest.render.world.renderList;

import java.util.Comparator;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Comparator for {@link RenderableObject}. Used for deciding which object
 * should be rendered first.
 * 
 * Sorting rules order: height, tilePosition(lower coordinates first).
 * 
 * @author adamsko
 * 
 */
public class RenderableObjectComparator implements Comparator <RenderableObject> {

	@Override
	public int compare(RenderableObject o1, RenderableObject o2) {
		
		if(o1.getRenderType() != o2.getRenderType()) {
			Gdx.app.error("RenderableObjectComparator", "o1.getRenderType() != o2.getRenderType()");
			return 0;
		}

		switch (o1.getRenderType()) {
		case TYPE_WORLD:
			WorldObject wo1 = (WorldObject)o1;
			WorldObject wo2 = (WorldObject)o2;

			if (wo1.getVerticalPos() < wo2.getVerticalPos())
				return -1;
			if (wo1.getVerticalPos() > wo2.getVerticalPos())
				return 1;
			// objects have equal height

			Vector2 wo1TP = wo1.getTilesPos();
			Vector2 wo2TP = wo2.getTilesPos();
			if (wo1TP.x < wo2TP.x)
				return -1;
			if (wo1TP.x > wo2TP.x)
				return 1;
			if (wo1TP.y < wo2TP.y)
				return -1;
			if (wo1TP.y > wo2TP.y)
				return 1;

			return 0;
			
		case TYPE_GUI:
			return 0;
			
		default: 
			Gdx.app.error("RenderableObjectComparator", "unsupported getRenderType");
			return 0;
		}
	}
}
