package org.adamsko.cubicforest.pickmaster;

import com.badlogic.gdx.math.Vector2;

public interface PickMasterClient {
	void onLeftClickScreen(Vector2 screenPos);
	void onRightClickScreen(Vector2 screenPos);
	void onLeftClickTiles(Vector2 tilesPos);
	void onRightClickTiles(Vector2 tilesPos);
}
