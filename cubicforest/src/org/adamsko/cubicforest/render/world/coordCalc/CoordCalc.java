package org.adamsko.cubicforest.render.world.coordCalc;

import com.badlogic.gdx.math.Vector2;

public interface CoordCalc {
	Vector2 tilesToRender(final Vector2 tilePos);

	Vector2 screenToTiles(final Vector2 screenPos);
}
