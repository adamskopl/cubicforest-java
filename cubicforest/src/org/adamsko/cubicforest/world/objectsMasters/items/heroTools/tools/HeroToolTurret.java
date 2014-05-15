package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTurret extends HeroTool {

	public HeroToolTurret(TextureRegion tr, int texNum) {
		super(tr, texNum, HeroToolType_e.TOOL_TURRET);

	}

	@Override
	public void onEntityTileEvent(EntityObject entityObject,
			TileEvent_e eventType) {
		// TODO Auto-generated method stub
		
	}

}
