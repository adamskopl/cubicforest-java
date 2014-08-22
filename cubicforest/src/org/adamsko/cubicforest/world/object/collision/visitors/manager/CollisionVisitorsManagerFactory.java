package org.adamsko.cubicforest.world.object.collision.visitors.manager;

import java.util.HashMap;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.collision.master.CollisionsMaster;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.HeroCollisionVisitorEnter;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.HeroCollisionVisitorStop;

public class CollisionVisitorsManagerFactory {

	private static CollisionVisitorsManagerFactory instance = null;

	private final HashMap<WorldObjectType, CollisionVisitorsManager> managers;
	private CollisionsMaster collisionsMaster;

	private CollisionVisitorsManagerFactory() {
		managers = new HashMap<WorldObjectType, CollisionVisitorsManager>();
	}

	public static CollisionVisitorsManagerFactory instance() {
		if (instance == null) {
			instance = new CollisionVisitorsManagerFactory();
		}
		return instance;
	}

	public void setCollisionsMaster(final CollisionsMaster collisionsMaster) {
		this.collisionsMaster = collisionsMaster;
	}

	public CollisionVisitorsManager create(final WorldObjectType managerType) {

		if (!managers.containsKey(managerType)) {
			final CollisionVisitorsManagerDefault missingManager = new CollisionVisitorsManagerDefault();

			switch (managerType) {
			case HERO:
				missingManager.setVisitorEnter(new HeroCollisionVisitorEnter(
						collisionsMaster));
				missingManager.setVisitorStop(new HeroCollisionVisitorStop(
						collisionsMaster));
				break;
			default:

			}
			managers.put(managerType, missingManager);
		}

		return managers.get(managerType);
	}
}
