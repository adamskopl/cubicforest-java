package org.adamsko.cubicforest.world.pickmaster;

import org.adamsko.cubicforest.Nullable;

import com.badlogic.gdx.math.Vector2;

public interface PickMasterClient extends Nullable {
	void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos);
}
