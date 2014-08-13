package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolver;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class CollisionResolverGatherCubes implements CollisionResolver {

	private GatherCubesMaster gatherCubesMaster;

	public CollisionResolverGatherCubes(GatherCubesMaster gatherCubesMaster) {
		this.gatherCubesMaster = gatherCubesMaster;
	}

	@Override
	public CollisionResult resolveCollision(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		CollisionResult collisionResult = new CollisionResult(eventTile,
				eventObject);

		ItemObject item = (ItemObject) eventTile.getItem();
		if (item.getItemType() != ItemObjectType.ITEM_GATHER_CUBE) {
			return collisionResult;
		}

		GatherCube tileCube = (GatherCube) item;

		switch (eventType) {
		case OCCUPANT_ENTERS:
			gatherCubesMaster.cubeHighlight(tileCube);
			break;

		case OCCUPANT_LEAVES:
			gatherCubesMaster.cubeUnHighlight(tileCube);
			break;

		case OCCUPANT_STOPS:
			gatherCubesMaster.cubeUnHighlight(tileCube);
			gatherCubesMaster.counterAddValue(1);
			collisionResult.remove(tileCube);
			break;
		default:
			break;
		}

		return collisionResult;
	}

	@Override
	public CollisionResolverType_e getType() {
		return CollisionResolverType_e.RESOLVER_GATHER_CUBES;
	}

}
