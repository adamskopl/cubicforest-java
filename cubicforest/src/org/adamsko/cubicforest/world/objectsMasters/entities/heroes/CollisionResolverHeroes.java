package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolver;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObjectType;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.Gdx;

public class CollisionResolverHeroes implements CollisionResolver {

	public CollisionResolverHeroes(HeroesMaster heroesMaster) {
	}

	@Override
	public CollisionResult resolveInteracion(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		CollisionResult collisionResult = new CollisionResult(eventTile,
				eventObject);

		if (!eventTileHasHero(eventTile)) {
			return collisionResult;
		}
		
		Hero tileHero = (Hero) eventTile.getOccupant();

		if (eventObject.getType() == WorldObjectType.OBJECT_ENTITY) {
			EntityObject eventEntity = (EntityObject) eventObject;
			switch (eventEntity.getEntityType()) {
			case ENTITY_ENEMY:
				resolveCollisionEnemy(collisionResult, eventType, tileHero,
						(Enemy) eventEntity);
				break;
			default:
			}
		}

		return collisionResult;
	}

	@Override
	public CollisionResolverType_e getType() {
		return CollisionResolverType_e.RESOLVER_HEROES;
	}

	private boolean eventTileHasHero(Tile eventTile) {

		WorldObject worldObjectTile = eventTile.getOccupant();

		if (worldObjectTile.getType() != WorldObjectType.OBJECT_ENTITY) {
			return false;
		}

		EntityObject entityObjectTile = (EntityObject) worldObjectTile;

		if (entityObjectTile.getEntityType() != EntityObjectType.ENTITY_HERO) {
			return false;
		}

		return true;
	}

	private void resolveCollisionEnemy(CollisionResult collisionResult,
			TileEvent eventType, Hero hero, Enemy enemy) {
		
		if(eventType == TileEvent.OCCUPANT_ENTERS) {
			collisionResult.remove(hero);
			collisionResult.setGameResult(GameResult.GAME_LOST);
		}
	}

}
