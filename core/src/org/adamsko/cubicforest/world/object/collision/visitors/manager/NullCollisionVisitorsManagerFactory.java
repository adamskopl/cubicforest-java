package org.adamsko.cubicforest.world.object.collision.visitors.manager;


public class NullCollisionVisitorsManagerFactory extends
		CollisionVisitorsManagerFactory {
	private static NullCollisionVisitorsManagerFactory instance = null;

	private NullCollisionVisitorsManagerFactory() {
		super();
	}

	public static NullCollisionVisitorsManagerFactory instance() {
		if (instance == null) {
			instance = new NullCollisionVisitorsManagerFactory();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
