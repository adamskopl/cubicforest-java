package org.adamsko.cubicforest.pickmaster;

import com.badlogic.gdx.math.Vector2;

public interface PickMasterClient {
	void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos);
}
