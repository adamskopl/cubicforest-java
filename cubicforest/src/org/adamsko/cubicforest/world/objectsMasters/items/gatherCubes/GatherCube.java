package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GatherCube extends ItemObject {

	public GatherCube(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container) {
		super(tr, texNum, container, ItemObjectType.ITEM_GATHER_CUBE);
	}

	@Override
	protected void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTilePathSearchValid(true);
	}

}
