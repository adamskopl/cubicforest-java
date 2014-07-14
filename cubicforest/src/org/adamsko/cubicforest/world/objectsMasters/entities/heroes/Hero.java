package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends EntityObject {

	public Hero(TextureRegion tr, int texNum) {
		super(tr, texNum, EntityObjectType.ENTITY_HERO);
	}

}
