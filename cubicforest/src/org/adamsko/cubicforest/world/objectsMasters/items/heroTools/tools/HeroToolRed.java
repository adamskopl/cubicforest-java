package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolRed extends HeroTool {

	public HeroToolRed(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container) {
		super(tr, texNum, container, HeroToolType.TOOL_RED);

	}

	@Override
	public void onEntityTileEvent(final CollisionResult collisionResult,
			final EntityObject entityObject, final TileEvent eventType) {
		// TODO Auto-generated method stub

	}

}
