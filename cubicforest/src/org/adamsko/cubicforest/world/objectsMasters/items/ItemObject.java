package org.adamsko.cubicforest.world.objectsMasters.items;

import org.adamsko.cubicforest.world.object.Type;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemObject extends WorldObject {

	private final ItemObjectType itemType;

	public ItemObject(final TextureRegion tr, final int texNum,
			final ItemObjectType itemType) {
		super(tr, texNum, Type.OBJECT_ITEM);
		this.itemType = itemType;
	}

	public ItemObjectType getItemType() {
		return itemType;
	}

}
