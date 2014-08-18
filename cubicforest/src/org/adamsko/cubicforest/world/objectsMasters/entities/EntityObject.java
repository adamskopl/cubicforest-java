package org.adamsko.cubicforest.world.objectsMasters.entities;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityObject extends WorldObject {

	private final EntityObjectType entityType;

	public EntityObjectType getEntityType() {
		return entityType;
	}

	public EntityObject(final TextureRegion tr, final int texNum,
			final EntityObjectType entityType) {
		super(tr, texNum, WorldObjectType.OBJECT_ENTITY);
		this.entityType = entityType;
	}

}
