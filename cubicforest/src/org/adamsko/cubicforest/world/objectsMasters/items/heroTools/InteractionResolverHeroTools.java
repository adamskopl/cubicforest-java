package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolver;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public class InteractionResolverHeroTools implements InteractionResolver {

	private HeroesToolsMaster heroesToolsMaster;
	private GatherCubesMaster gatherCubesMaster;

	public InteractionResolverHeroTools(HeroesToolsMaster heroesToolsMaster,
			GatherCubesMaster gatherCubesMaster, EnemiesMaster enemiesMaster) {
		this.heroesToolsMaster = heroesToolsMaster;
		this.gatherCubesMaster = gatherCubesMaster;
	}

	@Override
	public InteractionResult resolveInteracion(TileEvent eventType,
			Tile eventTile, WorldObject eventObject) {

		InteractionResult interactionResult = new InteractionResult(eventTile,
				eventObject);

		ItemObject item = (ItemObject) eventTile.getItem();

		if (item.getItemType() != ItemObjectType.ITEM_HERO_TOOL) {
			return interactionResult;
		}

		HeroTool tileTool = (HeroTool) item;
		HeroToolStates_e toolState = tileTool.getState();

		/*
		 * Build tool or resolve interaction for particular type of tool?
		 */
		if (toolState == HeroToolStates_e.STATE_CONSTRUCTION) {
			if (eventType == TileEvent.OCCUPANT_STOPS
					|| eventType == TileEvent.OCCUPANT_STAYS) {
				heroesToolsMaster.setToolTexture(tileTool, 0);
				tileTool.setState(HeroToolStates_e.STATE_READY);
				gatherCubesMaster.counterAddValue(-tileTool.getBuildCost());
			}
		} else if (eventObject.getType() == WorldObjectType.OBJECT_ENTITY) {
			tileTool.onEntityTileEvent(interactionResult,
					(EntityObject) eventObject, eventType);
		}

		return interactionResult;
	}

	@Override
	public InteractionResolverType_e getType() {
		return InteractionResolverType_e.RESOLVER_HERO_TOOLS;
	}

}
