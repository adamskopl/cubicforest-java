package org.adamsko.cubicforest.render.world.coordCalc;

import org.adamsko.cubicforest.Nullable;

import com.badlogic.gdx.math.Vector2;

/**
 * Converting coordinates in different spaces. <br>
 * TilesCoordinates - coordinates indicated by tiles. (0.0, 0.0) is a left
 * corner of the first tile, (0.5, 0.5) is it's center. <br>
 * RenderCoordinates - coordinates indicated by render area. E.g. if a tile is
 * 128x128px and is displayed isometricaly, (1.0, 0.0) in TilesCoordinates is
 * (64.0, 32.0) in RenderCoordinates. <br>
 * 
 * ScreenCoordinates - coordinates indicated by screen's dimensions.
 * 
 * @author adamsko
 */
public class CoordCalcDefault implements CoordCalc, Nullable {

	// rendered tile: width
	private static float tileW;
	// rendered tile: height
	private static float tileH;

	public CoordCalcDefault(final float tileSize) {
		CoordCalcDefault.tileW = tileSize;
		// tile's height is 2 times smaller than width
		CoordCalcDefault.tileH = tileSize / 2;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	/**
	 * @param tilePos
	 *            Coordinates in the tiles space. (0.0, 0.0) is the left upper
	 *            corner of the first tile. (1.5, 2.5) is the center of the (1,
	 *            2) tile.
	 * @return Coordinates in the render space.
	 */
	@Override
	public Vector2 tilesToRender(final Vector2 tilePos) {
		final Vector2 renderPos = new Vector2();
		renderPos.x = (tilePos.x - tilePos.y) * tileW / 2;
		renderPos.y = -(tilePos.x + tilePos.y) * tileH / 2;
		return renderPos;
	}

	/*
	 * TODO camera position needed (translating coordinates)
	 */
	@Override
	public Vector2 screenToTiles(final Vector2 screenPos) {
		final Vector2 tilesPos = new Vector2();
		tilesPos.x = screenPos.x / tileW + screenPos.y / tileH;
		tilesPos.y = screenPos.y / tileH - screenPos.x / tileW;
		return tilesPos;
	}

}