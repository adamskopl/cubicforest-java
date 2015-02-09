package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.math.Vector2;

public interface OrderDecision extends Nullable {

	void setChosenHeroTool(final WorldObjectType chosenHeroTool);

	WorldObjectType getChosenHeroTool();

	/**
	 * Check if order decision contains tool decision.
	 */
	boolean heroToolChosen();

	Vector2 getChosenTilePos();

}
