package org.adamsko.cubicforest.render.world.coordCalc;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface CoordCalc {

	/**
	 * Converts tile position to render position. Returned coordinates should be
	 * ready to pass them to {@link SpriteBatch} render.
	 * 
	 * @param tilePos
	 *            Coordinates in the tiles space. (0.0, 0.0) is the left upper
	 *            corner of the first tile. (1.5, 2.5) is the center of the (1,
	 *            2) tile.
	 * @return Coordinates in the render space.
	 */
	Vector2 tilesToRender(final Vector2 tilePos);

	/**
	 * Converts screen position to tile position (e.g to check which tile is
	 * chosen with given screen input)
	 * 
	 * @param screenPos
	 */
	Vector2 screenToTiles(final Vector2 screenPos);

}
