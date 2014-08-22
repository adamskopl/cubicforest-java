package org.adamsko.cubicforest.world.object.collision.visitors.manager;

import java.util.HashMap;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.EnemyCollisionVisitorEnter;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.EnemyCollisionVisitorStop;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.HeroCollisionVisitorEnter;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.HeroCollisionVisitorStop;

public class CollisionVisitorsManagerFactory {

	private static CollisionVisitorsManagerFactory instance = null;

	private final HashMap<WorldObjectType, CollisionVisitorsManager> managers;
	private CollisionsHandler collisionsHandler;

	private CollisionVisitorsManagerFactory() {
		managers = new HashMap<WorldObjectType, CollisionVisitorsManager>();
	}

	public static CollisionVisitorsManagerFactory instance() {
		if (instance == null) {
			instance = new CollisionVisitorsManagerFactory();
		}
		return instance;
	}

	public void setCollisionsHandler(final CollisionsHandler collisionsHandler) {
		this.collisionsHandler = collisionsHandler;
	}

	public CollisionVisitorsManager create(final WorldObjectType managerType) {

		if (!managers.containsKey(managerType)) {
			final CollisionVisitorsManagerDefault missingManager = new CollisionVisitorsManagerDefault();

			switch (managerType) {
			case HERO:
				missingManager.setVisitorEnter(new HeroCollisionVisitorEnter(
						collisionsHandler));
				missingManager.setVisitorStop(new HeroCollisionVisitorStop(
						collisionsHandler));
				break;

			case ENEMY:
				missingManager.setVisitorEnter(new EnemyCollisionVisitorEnter(
						collisionsHandler));
				missingManager.setVisitorStop(new EnemyCollisionVisitorStop(
						collisionsHandler));
				break;
			default:

			}
			managers.put(managerType, missingManager);
		}

		return managers.get(managerType);
	}
}