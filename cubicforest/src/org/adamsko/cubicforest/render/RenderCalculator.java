package org.adamsko.cubicforest.render;

import com.badlogic.gdx.math.Vector2;

public class RenderCalculator {

	// width of the rendered isometric tile (in pixels)
	int tileWidth;
	
	/**
	 * @param tileWidth width of the rendered isometric tile (in pixels)
	 */
	public RenderCalculator(int tileWidth) {
		this.tileWidth = tileWidth;
	}
	
	/**
	 * @param worldCoords Coordinates in the world space. 
	 * (0.0, 0.0) is the left upper corner of the first tile.
	 * (1.5, 2.5) is the center of the (1, 2) tile.
	 * @return Coordinates in the render space (screen coordinates).
	 */
	public Vector2 getScreenCoord(Vector2 worldCoords) {
		Vector2 screenCords = new Vector2();
		screenCords.x = (worldCoords.x - worldCoords.y) * tileWidth/2;
		// tile's height is 2 times smaller than width
		screenCords.y = -(worldCoords.x + worldCoords.y) * tileWidth/4;
		return screenCords;
	}
	
}