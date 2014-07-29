package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolver;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class InteractionResolverGatherCubes implements InteractionResolver {

	private GatherCubesMaster gatherCubesMaster;

	public InteractionResolverGatherCubes(GatherCubesMaster gatherCubesMaster) {
		this.gatherCubesMaster = gatherCubesMaster;
	}

	@Override
	public InteractionResult resolveInteracion(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		InteractionResult interactionResult = new InteractionResult(eventTile,
				eventObject);

		ItemObject item = (ItemObject) eventTile.getItem();
		if (item.getItemType() != ItemObjectType.ITEM_GATHER_CUBE) {
			return interactionResult;
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
			interactionResult.remove(tileCube);
			break;
		default:
			break;
		}

		return interactionResult;
	}

	@Override
	public InteractionResolverType_e getType() {
		return InteractionResolverType_e.RESOLVER_GATHER_CUBES;
	}

}
