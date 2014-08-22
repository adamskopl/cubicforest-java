package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.master.CollisionsMaster;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTrap;

public class EnemyCollisionVisitorStop extends CollisionVisitorDefault {

	public EnemyCollisionVisitorStop(final CollisionsMaster collisionsMaster) {
		super(collisionsMaster);
	}

	@Override
	public void visitToolTrap(final HeroToolTrap heroToolTrap) {
	}

}
