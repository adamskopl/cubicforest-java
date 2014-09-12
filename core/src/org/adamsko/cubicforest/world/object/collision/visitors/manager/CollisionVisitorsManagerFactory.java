package org.adamsko.cubicforest.world.object.collision.visitors.manager;

import java.util.HashMap;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.handler.concrete.NullCollisionsHandlerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.NullCollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.EnemyCollisionVisitorEnter;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.EnemyCollisionVisitorPass;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.EnemyCollisionVisitorStop;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.HeroCollisionVisitorEnter;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.HeroCollisionVisitorLeave;
import org.adamsko.cubicforest.world.object.collision.visitors.concrete.HeroCollisionVisitorStop;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.PortalsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;

import com.badlogic.gdx.Gdx;

/**
 * Creates {@link CollisionVisitorsManager} objects. For every
 * {@link WorldObjectType} there is one manager kept in {@link #managers}
 * 
 * @author adamsko
 * 
 */
public class CollisionVisitorsManagerFactory implements Nullable {

	private HashMap<WorldObjectType, CollisionVisitorsManager> managers;
	private CollisionsHandler collisionsHandler;
	private GatherCubesMaster gatherCubesMaster;
	private HeroesToolsMaster heroesToolsMaster;
	private PortalsMaster portalsMaster;
	private PrizesMaster prizesMaster;

	/**
	 * For NullCollisionVisitorsManagerFactory
	 */
	CollisionVisitorsManagerFactory() {
	}

	public CollisionVisitorsManagerFactory(
			final GatherCubesMaster gatherCubesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final PortalsMaster portalsMaster, final PrizesMaster prizesMaster) {

		managers = new HashMap<WorldObjectType, CollisionVisitorsManager>();

		this.collisionsHandler = NullCollisionsHandlerDefault.instance();
		this.gatherCubesMaster = gatherCubesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		this.portalsMaster = portalsMaster;
		this.prizesMaster = prizesMaster;

		if (gatherCubesMaster.isNull()) {
			Gdx.app.error("CollisionVisitorsManagerFactory()",
					"gatherCubesMaster.isNull()");
		}
		if (heroesToolsMaster.isNull()) {
			Gdx.app.error("CollisionVisitorsManagerFactory()",
					"heroesToolsMaster.isNull()");
		}
		if (portalsMaster.isNull()) {
			Gdx.app.error("CollisionVisitorsManagerFactory()",
					"portalsMaster.isNull()");
		}
		if (prizesMaster.isNull()) {
			Gdx.app.error("CollisionVisitorsManagerFactory()",
					"prizesMaster.isNull()");
		}

	}

	@Override
	public boolean isNull() {
		return false;
	}

	public void setCollisionsHandler(final CollisionsHandler collisionsHandler) {
		if (collisionsHandler.isNull()) {
			Gdx.app.error(
					"CollisionVisitorsManagerFactory::setCollisionsHandler()",
					"collisionsHandler.isNull()");
		}
		this.collisionsHandler = collisionsHandler;
	}

	/**
	 * Creates one instance of manager for every {@link WorldObjectType}, so
	 * objects of the same type, share on manager.
	 * 
	 * @param managerType
	 */
	public CollisionVisitorsManager create(final WorldObjectType managerType) {

		if (collisionsHandler.isNull()) {
			Gdx.app.error("CollisionVisitorsManagerFactory::create()",
					"collisionsHandler.isNull()");
			return NullCollisionVisitorsManagerDefault.instance();
		}

		if (!managers.containsKey(managerType)) {
			final CollisionVisitorsManagerDefault missingManager = new CollisionVisitorsManagerDefault();

			switch (managerType) {
			case HERO:
				missingManager.setVisitorEnter(new HeroCollisionVisitorEnter(
						collisionsHandler, gatherCubesMaster, portalsMaster,
						prizesMaster));
				missingManager
						.setVisitorStop(new HeroCollisionVisitorStop(
								collisionsHandler, gatherCubesMaster,
								heroesToolsMaster));

				missingManager.setVisitorLeave(new HeroCollisionVisitorLeave(
						collisionsHandler, gatherCubesMaster, portalsMaster,
						prizesMaster));
				break;

			case ENEMY:
				missingManager.setVisitorEnter(new EnemyCollisionVisitorEnter(
						collisionsHandler));
				missingManager.setVisitorStop(new EnemyCollisionVisitorStop(
						collisionsHandler));
				missingManager.setVisitorPass(new EnemyCollisionVisitorPass(
						collisionsHandler));
				break;
			default:

			}
			managers.put(managerType, missingManager);
		}

		return managers.get(managerType);
	}
}
