package org.adamsko.cubicforest.world.objectsMasters.entities;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityObject extends WorldObject {

	private final EntityObjectType entityType;

	public EntityObjectType getEntityType() {
		return entityType;
	}

	public EntityObject(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container,
			final EntityObjectType entityType,
			final WorldObjectType worldObjectType) {
		super(tr, texNum, container, WorldObjectType.OBJECT_ENTITY,
				worldObjectType);
		this.entityType = entityType;
	}

}
