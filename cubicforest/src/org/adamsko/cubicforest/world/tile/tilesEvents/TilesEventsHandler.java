package org.adamsko.cubicforest.world.tile.tilesEvents;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.object.collision.handler.concrete.CollisionsHandlerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResultProcessor;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;

public class TilesEventsHandler {

	private final TilesContainer tilesContainer;
	private CollisionResultProcessor collisionResultProcessor;
	private final CollisionsHandler collisionsHandler;

	public TilesEventsHandler(final TilesContainer tilesContainer,
			final RoundsMaster roundsMaster) {
		this.tilesContainer = tilesContainer;

		collisionsHandler = new CollisionsHandlerDefault(roundsMaster);

		CollisionVisitorsManagerFactory.instance().setCollisionsHandler(
				collisionsHandler);
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
	public OrderOperationHandler tileEvent(final TileEvent evenType,
			final Tile eventTile, final WorldObject eventObject)
			throws Exception {

		collisionsHandler.collision(evenType, eventTile, eventObject);

		// if active object is not removed after tile event, perform common
		// operations: object insert/take out
		if (eventObject.getState() != WorldObjectState.DEAD) {
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
			default:
			}
		}

		return collisionsHandler.orderOperation();
	}

}
