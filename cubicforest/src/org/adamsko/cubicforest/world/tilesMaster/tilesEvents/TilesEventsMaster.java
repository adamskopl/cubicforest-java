package org.adamsko.cubicforest.world.tilesMaster.tilesEvents;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionsMaster;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResultProcessor;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class TilesEventsMaster {

	private final TilesContainer tilesContainer;
	private CollisionsMaster collisionMaster;
	private CollisionResultProcessor collisionResultProcessor;

	public TilesEventsMaster(final TilesContainer tilesContainer) {
		this.tilesContainer = tilesContainer;
	}

	public void setCollisionMaster(final CollisionsMaster collisionMaster) {
		this.collisionMaster = collisionMaster;
	}

	public void initCollisionResultProcessor(final HeroesMaster heroesMaster,
			final EnemiesMaster enemiesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final GatherCubesMaster gatherCubesMaster,
			final RoundsMaster roundsMaster, final PhaseEnemies phaseEnemies,
			final PhaseHeroes phaseHeroes) {

		collisionResultProcessor = new CollisionResultProcessor(heroesMaster,
				enemiesMaster, heroesToolsMaster, gatherCubesMaster);
		collisionResultProcessor.initPhases(roundsMaster, phaseEnemies,
				phaseHeroes);

	}

	/**
	 * Handle an event: {@link Tile} + {@link WorldObject}
	 * 
	 * @param evenType
	 * @param eventTile
	 * @param eventObject
	 * @throws Exception
	 */
	public CollisionResult tileEvent(final TileEvent evenType,
			final Tile eventTile, final WorldObject eventObject)
			throws Exception {

		final CollisionResult collisionResult = collisionMaster.tileEvent(
				evenType, eventTile, eventObject);

		// collision results should be resolved
		collisionResultProcessor.resolve(collisionResult);

		// if active object is not removed after tile event, perform common
		// operations: object insert/take out
		if (collisionResult.getOrderObjectOperation() != ObjectOperation.OBJECT_REMOVE) {
			switch (evenType) {
			case OCCUPANT_ENTERS: {
				eventTile.addOccupant(eventObject, true);
				tilesContainer.testHighlightTile(eventTile, 0, 1);
				break;
			}
			case OCCUPANT_LEAVES: {
				if (Tile.occupantsRefactor) {
					eventTile.removeOccupant(eventObject);
				} else {
					eventTile.occupantLeaves();
				}

				tilesContainer.testHighlightTile(eventTile, 0, 0);
				break;
			}
			case OCCUPANT_STOPS: {
				break;
			}
			case OCCUPANT_PASSES: {
				break;
			}
			case OCCUPANT_STAYS: {
				break;
			}
			default: {
				throw new Exception("tileEvent: unsupported event type");
			}
			}
		}

		return collisionResult;
	}

	/**
	 * @param evenType
	 * @param eventTile
	 * @throws Exception
	 */
	public void tileEvent(final TileEvent evenType, final Tile eventTile)
			throws Exception {

		switch (evenType) {
		case OCCUPANT_ENTERS: {

			break;
		}
		case OCCUPANT_LEAVES: {

			break;
		}
		default: {
			throw new Exception("tileEvent: unsupported event type");
		}
		}

	}
}
