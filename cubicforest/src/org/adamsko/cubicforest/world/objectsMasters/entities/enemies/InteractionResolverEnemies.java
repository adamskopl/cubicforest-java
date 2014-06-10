package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolver;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResult;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public class InteractionResolverEnemies implements InteractionResolver {

	@Override
	public InteractionResult resolveInteracion(TileEvent_e eventType,
			Tile eventTile, WorldObject eventObject) {
		InteractionResult interactionResult = new InteractionResult(
				OrderOperation_e.ORDER_CONTINUE);
		return interactionResult;
	}

	@Override
	public InteractionResolverType_e getType() {
		return InteractionResolverType_e.RESOLVER_ENEMIES;
	}

}
