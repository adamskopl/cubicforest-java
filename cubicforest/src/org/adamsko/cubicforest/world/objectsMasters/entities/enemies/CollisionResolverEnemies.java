package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolver;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class CollisionResolverEnemies implements CollisionResolver {

	@Override
	public CollisionResult resolveInteracion(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		CollisionResult collisionResult = new CollisionResult(eventTile,
				eventObject);

		return collisionResult;
	}

	@Override
	public CollisionResolverType_e getType() {
		return CollisionResolverType_e.RESOLVER_ENEMIES;
	}

}
