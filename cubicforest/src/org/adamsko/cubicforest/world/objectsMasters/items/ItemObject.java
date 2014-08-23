package org.adamsko.cubicforest.world.objectsMasters.items;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemObject extends WorldObject {

	private final ItemObjectType itemType;

	public ItemObject(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container, final ItemObjectType itemType) {
		super(tr, texNum, container, WorldObjectType.OBJECT_ITEM,
				WorldObjectType.OBJECT_ITEM);
		this.itemType = itemType;
	}

	public ItemObjectType getItemType() {
		return itemType;
	}

}
