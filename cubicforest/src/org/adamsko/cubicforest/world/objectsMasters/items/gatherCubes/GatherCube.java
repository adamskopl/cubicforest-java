package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GatherCube extends ItemObject {
	
	public GatherCube(TextureRegion tr, int texNum) {
		super(tr, texNum, ItemObjectType_e.ITEM_GATHER_CUBE);
	}

}
