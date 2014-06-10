package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation_e;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolver;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class InteractionResolverHeroTools implements InteractionResolver {

	private HeroesToolsMaster heroesToolsMaster;
	private GatherCubesMaster gatherCubesMaster;
	private EnemiesMaster enemiesMaster;

	public InteractionResolverHeroTools(HeroesToolsMaster heroesToolsMaster,
			GatherCubesMaster gatherCubesMaster, EnemiesMaster enemiesMaster) {
		this.heroesToolsMaster = heroesToolsMaster;
		this.gatherCubesMaster = gatherCubesMaster;
		this.enemiesMaster = enemiesMaster;
	}

	@Override
	public InteractionResult resolveInteracion(TileEvent_e eventType,
			Tile eventTile, WorldObject eventObject) {

		InteractionResult interactionResult = new InteractionResult();

		ItemObject item = (ItemObject) eventTile.getItem();
		if (item.getItemType() != ItemObjectType_e.ITEM_HERO_TOOL) {
			interactionResult
					.setOrderOperation(OrderOperation_e.ORDER_CONTINUE);
			return interactionResult;
		}

		HeroTool tileTool = (HeroTool) item;

		if (eventObject.getWorldType() == WorldObjectType_e.OBJECT_ENTITY) {
			interactionResult = tileTool.onEntityTileEvent(
					(EntityObject) eventObject, eventType);


		}

		if (eventType == TileEvent_e.OCCUPANT_STOPS) {
			// for now only one situation: Hero is heading right now to finish
			// up a tool
			HeroToolStates_e toolState = tileTool.getState();
			switch (toolState) {
			case STATE_CONSTRUCTION:
				heroesToolsMaster.setToolTexture(tileTool, 0);
				tileTool.setState(HeroToolStates_e.STATE_READY);
				gatherCubesMaster.counterAddValue(-tileTool.getBuildCost());
				break;
			case STATE_READY:
				break;
			}
		}

		BŁĄD TUTAJ: DLACZEGO ENEMY SIĘ NIE ZATRZYMUJE?
		if (interactionResult.getTileObjectOperation() == ObjectOperation_e.OBJECT_REMOVE) {
			heroesToolsMaster.removeTool(tileTool);
		}
		
		return interactionResult;
	}

	@Override
	public InteractionResolverType_e getType() {
		return InteractionResolverType_e.RESOLVER_HERO_TOOLS;
	}

}
