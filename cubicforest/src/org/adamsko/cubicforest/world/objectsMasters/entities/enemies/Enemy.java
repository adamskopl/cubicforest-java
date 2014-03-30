package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.entities.Entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends Entity {

	public Enemy(TextureRegion tr, int texNum, WorldObjectType_e type) {
		super(tr, texNum, type);
	}


}
