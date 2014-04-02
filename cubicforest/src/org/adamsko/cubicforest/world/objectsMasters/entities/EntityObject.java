package org.adamsko.cubicforest.world.objectsMasters.entities;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityObject extends WorldObject {

	private EntityObjectType_e entityType;
	
	public EntityObject(TextureRegion tr, int texNum, EntityObjectType_e entityType) {
		super(tr, texNum, WorldObjectType_e.OBJECT_ENTITY);
		this.entityType = entityType;
	}

}
