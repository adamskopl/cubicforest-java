package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTrap extends HeroTool {

	public HeroToolTrap(TextureRegion tr, int texNum) {
		super(tr, texNum, HeroToolType.TOOL_TRAP);

	}

	@Override
	public void onEntityTileEvent(InteractionResult interactionResult,
			EntityObject entityObject, TileEvent eventType) {

		if (eventType != TileEvent.OCCUPANT_PASSES
				&& eventType != TileEvent.OCCUPANT_STOPS) {
			return;
		}

		switch (entityObject.getEntityType()) {
		case ENTITY_ENEMY:
			interactionResult.setOrderOperation(OrderOperation.ORDER_FINISH);

			// remove enemy
			interactionResult.remove((Enemy)entityObject);

			// remove trap
			interactionResult.remove(this);

		case ENTITY_HERO:
			break;
		default:
			break;
		}

	}

}
