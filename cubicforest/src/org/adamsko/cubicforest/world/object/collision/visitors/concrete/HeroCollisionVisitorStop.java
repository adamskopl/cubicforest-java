package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;

import com.badlogic.gdx.Gdx;

public class HeroCollisionVisitorStop extends CollisionVisitorDefault {
	public HeroCollisionVisitorStop(final CollisionsHandler collisionsHandler) {
		super(collisionsHandler);
	}

	@Override
	public void visitToolOrange(final HeroToolOrange heroToolOrange) {
		Gdx.app.debug("hero STOP", "visits tool orange");
	}
}
