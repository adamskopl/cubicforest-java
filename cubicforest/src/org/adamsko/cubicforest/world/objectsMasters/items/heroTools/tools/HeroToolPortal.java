package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolPortal extends HeroTool {

	private final HeroesMaster heroesMaster;

	public HeroToolPortal(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container,
			final HeroesMaster heroesMaster) {
		super(tr, texNum, container, HeroToolType.TOOL_PORTAL);
		this.heroesMaster = heroesMaster;
	}

	@Override
	public void onEntityTileEvent(final CollisionResult collisionResult,
			final EntityObject entityObject, final TileEvent eventType) {

		switch (entityObject.getEntityType()) {
		case ENTITY_HERO:
			if (!validHero(eventType)) {
				return;
			}
			collisionResult.setOrderOperation(OrderOperation.ORDER_FINISH);

			// remove hero
			collisionResult.remove((Hero) entityObject);

			// remove portal
			collisionResult.remove(this);

			// if removed hero is last, the game is won
			if (heroesMaster.getOrderableObjects().size() == 1) {
				collisionResult.setGameResult(GameResult.GAME_WON);
			}

			break;

		case ENTITY_ENEMY:
			if (!validEnemy(eventType)) {
				return;
			}
			/*
			 * The enemy is destroying the portal.
			 */
			collisionResult.remove(this);

		default:
			break;
		}

	}

	private Boolean validHero(final TileEvent eventType) {
		if (eventType != TileEvent.OCCUPANT_STOPS
				&& eventType != TileEvent.OCCUPANT_STAYS) {
			return false;
		}
		return true;
	}

	private Boolean validEnemy(final TileEvent eventType) {
		return true;
	}
}
