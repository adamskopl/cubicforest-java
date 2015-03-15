package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap.HeroToolTrap;

public class EnemyCollisionVisitorEnter extends CollisionVisitorDefault {

	public EnemyCollisionVisitorEnter(final CollisionsHandler collisionsHandler) {
		super(collisionsHandler);
	}

	@Override
	public void visitHero(final Hero hero) {
		collision().gameResultOperation().setGameLost();
		hero.visualState().changeState(
				RenderableObjectVisualState.SELECTED_FAIL);
	}

	@Override
	public void visitToolTrap(final HeroToolTrap heroToolTrap) {
		super.visitToolTrap(heroToolTrap);
		getVisitingObject().visualState().changeState(
				RenderableObjectVisualState.SELECTED_FAIL);
	}
}
