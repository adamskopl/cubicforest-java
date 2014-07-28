package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObjectType;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolver;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.Gdx;

public class InteractionResolverHeroes implements InteractionResolver {

	public InteractionResolverHeroes(HeroesMaster heroesMaster) {
	}

	@Override
	public InteractionResult resolveInteracion(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		InteractionResult interactionResult = new InteractionResult(eventTile,
				eventObject);

		if (!eventTileHasHero(eventTile)) {
			return interactionResult;
		}
		
		Hero tileHero = (Hero) eventTile.getOccupant();

		if (eventObject.getType() == WorldObjectType.OBJECT_ENTITY) {
			EntityObject eventEntity = (EntityObject) eventObject;
			switch (eventEntity.getEntityType()) {
			case ENTITY_ENEMY:
				resolveInteractionEnemy(interactionResult, eventType, tileHero,
						(Enemy) eventEntity);
				break;
			default:
			}
		}

		return interactionResult;
	}

	@Override
	public InteractionResolverType_e getType() {
		return InteractionResolverType_e.RESOLVER_HEROES;
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

	private void resolveInteractionEnemy(InteractionResult interactionResult,
			TileEvent eventType, Hero hero, Enemy enemy) {
		
		if(eventType == TileEvent.OCCUPANT_ENTERS) {
			interactionResult.remove(hero);
			interactionResult.setGameResult(GameResult.GAME_LOST);
		}
	}

}
