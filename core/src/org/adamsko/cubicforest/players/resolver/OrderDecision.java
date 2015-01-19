package org.adamsko.cubicforest.players.resolver;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;

import com.badlogic.gdx.math.Vector2;

public interface OrderDecision extends Nullable {

	void setChosenHeroTool(final HeroTool heroTool);

	HeroTool getChosenHeroTool();

	Vector2 getChosenTilePos();

}
