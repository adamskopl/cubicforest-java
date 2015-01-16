package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap.HeroToolTrap;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;

public class EnemyCollisionVisitorStop extends CollisionVisitorDefault {

	public EnemyCollisionVisitorStop(final CollisionsHandler collisionsHandler) {
		super(collisionsHandler);
	}

	@Override
	public void visitToolTrap(final HeroToolTrap heroToolTrap) {
		// same as in EnemyCollisionVisitorPass
		super.visitToolTrap(heroToolTrap);
		collision().orderOperation().setOrderOperation(
				OrderOperation.ORDER_FINISH);
		collision().wordlObjectOperation().remove(getVisitingObject());
		collision().wordlObjectOperation().remove(heroToolTrap);
	}

}
