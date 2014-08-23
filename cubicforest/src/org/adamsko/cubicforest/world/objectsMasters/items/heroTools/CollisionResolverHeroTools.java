package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolver;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class CollisionResolverHeroTools implements CollisionResolver {

	private final HeroesToolsMaster heroesToolsMaster;
	private final GatherCubesMaster gatherCubesMaster;

	public CollisionResolverHeroTools(
			final HeroesToolsMaster heroesToolsMaster,
			final GatherCubesMaster gatherCubesMaster,
			final EnemiesMaster enemiesMaster) {
		this.heroesToolsMaster = heroesToolsMaster;
		this.gatherCubesMaster = gatherCubesMaster;
	}

	@Override
	public CollisionResult resolveCollision(final TileEvent eventType,
			final Tile eventTile, final WorldObject eventObject) {

		final CollisionResult collisionResult = new CollisionResult(eventTile,
				eventObject);

		final ItemObject item = (ItemObject) eventTile.getItem();

		if (item.getItemType() != ItemObjectType.ITEM_HERO_TOOL) {
			return collisionResult;
		}

		final HeroTool tileTool = (HeroTool) item;
		final HeroToolStates_e toolState = tileTool.getToolState();

		/*
		 * Build tool or resolve collision for particular type of tool?
		 */
		if (toolState == HeroToolStates_e.STATE_CONSTRUCTION) {
			if (eventType == TileEvent.OCCUPANT_STOPS
					|| eventType == TileEvent.OCCUPANT_STAYS) {
				heroesToolsMaster.setToolTexture(tileTool, 0);
				tileTool.setState(HeroToolStates_e.STATE_READY);
				gatherCubesMaster.counterAddValue(-tileTool.getBuildCost());
			}
		} else if (eventObject.getType() == WorldObjectType.OBJECT_ENTITY) {
			tileTool.onEntityTileEvent(collisionResult,
					(EntityObject) eventObject, eventType);
		}

		return collisionResult;
	}

	@Override
	public CollisionResolverType_e getType() {
		return CollisionResolverType_e.RESOLVER_HERO_TOOLS;
	}

}
