package org.adamsko.cubicforest.world.objectsMasters.entities;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityObject extends WorldObject {

	private EntityObjectType entityType;
	
	public EntityObjectType getEntityType() {
		return entityType;
	}

	public EntityObject(TextureRegion tr, int texNum, EntityObjectType entityType) {
		super(tr, texNum, WorldObjectType.OBJECT_ENTITY);
		this.entityType = entityType;
	}

}
