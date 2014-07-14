package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolRed extends HeroTool {

	public HeroToolRed(TextureRegion tr, int texNum) {
		super(tr, texNum, HeroToolType.TOOL_RED);

	}

	@Override
	public void onEntityTileEvent(InteractionResult interactionResult,
			EntityObject entityObject, TileEvent eventType) {
		// TODO Auto-generated method stub
		
	}

}
