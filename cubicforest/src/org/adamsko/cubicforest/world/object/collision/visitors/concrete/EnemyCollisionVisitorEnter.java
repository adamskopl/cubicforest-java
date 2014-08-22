package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.object.collision.master.CollisionsMaster;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;

public class EnemyCollisionVisitorEnter extends CollisionVisitorDefault {

	public EnemyCollisionVisitorEnter(final CollisionsMaster collisionsMaster) {
		super(collisionsMaster);
	}

	@Override
	public void visitHero(final Hero hero) {
		collision().gameResultOperation().setGameResult(GameResult.GAME_LOST);
	}
}
