package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation_e;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTrap extends HeroTool {

	public HeroToolTrap(TextureRegion tr, int texNum) {
		super(tr, texNum, HeroToolType_e.TOOL_TRAP);

	}

	@Override
	public InteractionResult onEntityTileEvent(EntityObject entityObject,
			TileEvent_e eventType) {

		InteractionResult interactionResult = new InteractionResult();

		switch (entityObject.getEntityType()) {
		case ENTITY_ENEMY:
			interactionResult.setOrderOperation(OrderOperation_e.ORDER_FINISH);

			// remove enemy
			interactionResult
					.setObjectOperation(ObjectOperation_e.OBJECT_REMOVE);
			
			// remove trap
			interactionResult
					.setTileObjectOperation(ObjectOperation_e.OBJECT_REMOVE);
			return interactionResult;
		case ENTITY_HERO:
			break;
		default:
			break;
		}

		return interactionResult;
	}

}
