package org.adamsko.cubicforest.world.objectsMasters.items;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemObject extends WorldObject {

	private ItemObjectType_e itemType;
	
	public ItemObject(TextureRegion tr, int texNum, ItemObjectType_e itemType) {
		super(tr, texNum, WorldObjectType.OBJECT_ITEM);
		this.itemType = itemType;
	}

	public ItemObjectType_e getItemType() {
		return itemType;
	}

}
