package org.adamsko.cubicforest.world.object.collision.visitors;


public class NullCollisionVisitorsManagerDefault extends
		CollisionVisitorsManagerDefault {
	private static NullCollisionVisitorsManagerDefault instance = null;

	private NullCollisionVisitorsManagerDefault() {
		super();
	}

	public static NullCollisionVisitorsManagerDefault instance() {
		if (instance == null) {
			instance = new NullCollisionVisitorsManagerDefault();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
