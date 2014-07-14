package org.adamsko.cubicforest.world.tilesMaster.tilesEvents;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResultProcessor;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class TilesEventsMaster {

	private TilesContainer tilesContainer;
	private InteractionMaster interactionMaster;
	private InteractionResultProcessor interactionResultProcessor;

	public TilesEventsMaster(TilesContainer tilesContainer) {
		this.tilesContainer = tilesContainer;
	}

	public void setInteractionMaster(InteractionMaster interactionMaster) {
		this.interactionMaster = interactionMaster;
	}
	
	public void initInteractionResultProcessor(HeroesMaster heroesMaster,
			EnemiesMaster enemiesMaster, HeroesToolsMaster heroesToolsMaster,
			GatherCubesMaster gatherCubesMaster, RoundsMaster roundsMaster, PhaseEnemies phaseEnemies,
			PhaseHeroes phaseHeroes) {

		interactionResultProcessor = new InteractionResultProcessor(
				heroesMaster, enemiesMaster, heroesToolsMaster,
				gatherCubesMaster);
		interactionResultProcessor.initPhases(roundsMaster, phaseEnemies, phaseHeroes);
		
	}

	/**
	 * Handle an event: {@link Tile} + {@link WorldObject}
	 * 
	 * @param evenType
	 * @param eventTile
	 * @param eventObject
	 * @throws Exception
	 */
	public InteractionResult tileEvent(TileEvent evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		InteractionResult interactionResult = interactionMaster.tileEvent(
				evenType, eventTile, eventObject);

		// interaction results should be resolved 
		interactionResultProcessor.resolve(interactionResult);		
		
		// if active object is not removed after tile event, perform common
		// operations: object insert/take out
		if (interactionResult.getOrderObjectOperation() != ObjectOperation.OBJECT_REMOVE) {
			switch (evenType) {
			case OCCUPANT_ENTERS: {
				eventTile.insertObject(eventObject, true);
				tilesContainer.testHighlightTile(eventTile, 0, 1);
				break;
			}
			case OCCUPANT_LEAVES: {
				eventTile.occupantLeaves();
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

		return interactionResult;
	}

	/**
	 * @param evenType
	 * @param eventTile
	 * @throws Exception
	 */
	public void tileEvent(TileEvent evenType, Tile eventTile) throws Exception {

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
