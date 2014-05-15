package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTrap extends HeroTool {

	public HeroToolTrap(TextureRegion tr, int texNum) {
		super(tr, texNum, HeroToolType_e.TOOL_TRAP);

	}

	@Override
	public void onEntityTileEvent(EntityObject entityObject,
			TileEvent_e eventType) {
		switch (entityObject.getEntityType()) {
		case ENTITY_ENEMY:
			Gdx.app.error("Trap", "ENEMY ENTERS!");
		case ENTITY_HERO:
			break;
		default:
			break;
		}

		
	}

}
