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

	private final Vector2 resultVector = new Vector2();

	public CoordCalcDefault(final float tileSize) {
		CoordCalcDefault.tileW = tileSize;
		// tile's height is 2 times smaller than width
		CoordCalcDefault.tileH = tileSize / 2;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public Vector2 tilesToRender(final Vector2 tilePos) {
		resultVector.x = (tilePos.x - tilePos.y) * tileW / 2;
		resultVector.y = -(tilePos.x + tilePos.y) * tileH / 2;
		return resultVector;
	}

	/*
	 * TODO camera position needed (translating coordinates)
	 */
	@Override
	public Vector2 screenToTiles(final Vector2 screenPos) {
		resultVector.x = screenPos.x / tileW + screenPos.y / tileH;
		resultVector.y = screenPos.y / tileH - screenPos.x / tileW;
		return resultVector;
	}

}