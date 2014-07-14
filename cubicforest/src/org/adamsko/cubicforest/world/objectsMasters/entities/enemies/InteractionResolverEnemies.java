package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolver;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class InteractionResolverEnemies implements InteractionResolver {

	@Override
	public InteractionResult resolveInteracion(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		InteractionResult interactionResult = new InteractionResult(eventTile,
				eventObject);

		return interactionResult;
	}

	@Override
	public InteractionResolverType_e getType() {
		return InteractionResolverType_e.RESOLVER_ENEMIES;
	}

}
