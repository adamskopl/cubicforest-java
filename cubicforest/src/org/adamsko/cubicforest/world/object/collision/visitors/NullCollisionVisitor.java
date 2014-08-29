package org.adamsko.cubicforest.world.object.collision.visitors;

public class NullCollisionVisitor extends CollisionVisitorDefault {

	public NullCollisionVisitor() {
		super();
	}

	private static NullCollisionVisitor instance = null;

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
