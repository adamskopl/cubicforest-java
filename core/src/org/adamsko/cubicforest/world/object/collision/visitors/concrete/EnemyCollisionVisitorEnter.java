package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;

public class EnemyCollisionVisitorEnter extends CollisionVisitorDefault {

	public EnemyCollisionVisitorEnter(final CollisionsHandler collisionsHandler) {
		super(collisionsHandler);
	}

	@Override
	public void visitHero(final Hero hero) {
		collision().gameResultOperation().setGameLost();
	}
}
