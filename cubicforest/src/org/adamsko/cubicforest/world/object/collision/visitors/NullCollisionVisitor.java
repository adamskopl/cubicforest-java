package org.adamsko.cubicforest.world.object.collision.visitors;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;

public class NullCollisionVisitor extends CollisionVisitorDefault {

	public NullCollisionVisitor(final CollisionsHandler collisionsHandler) {
		super(collisionsHandler);
	}

	private static NullCollisionVisitor instance = null;

	public static NullCollisionVisitor instance() {
		if (instance == null) {
			instance = new NullCollisionVisitor(null);
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
