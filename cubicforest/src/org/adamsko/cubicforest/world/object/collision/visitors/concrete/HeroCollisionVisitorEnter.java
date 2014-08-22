package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.master.CollisionsMaster;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;

import com.badlogic.gdx.Gdx;

public class HeroCollisionVisitorEnter extends CollisionVisitorDefault {

	public HeroCollisionVisitorEnter(final CollisionsMaster collisionsMaster) {
		super(collisionsMaster);
	}

	@Override
	public void visitToolOrange(final HeroToolOrange heroToolOrange) {
		Gdx.app.debug("hero ENTER", "visits tool orange");
	}
}
