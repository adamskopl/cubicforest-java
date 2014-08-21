package org.adamsko.cubicforest.world.object.collision.visitors;

public class NullCollisionVisitor extends CollisionVisitorDefault {

	private static NullCollisionVisitor instance = null;

	private NullCollisionVisitor() {
	}

	public static NullCollisionVisitor instance() {
		if (instance == null) {
			instance = new NullCollisionVisitor();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
