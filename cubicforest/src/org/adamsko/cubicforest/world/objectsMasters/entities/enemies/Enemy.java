package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends EntityObject {
	
	public Enemy(TextureRegion tr, int texNum) {
		super(tr, texNum, EntityObjectType_e.ENTITY_ENEMY);
	}


}
