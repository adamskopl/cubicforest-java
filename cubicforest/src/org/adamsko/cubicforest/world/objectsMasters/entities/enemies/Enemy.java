package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends WorldObject {

	public Enemy(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container) {
		super(tr, texNum, container, WorldObjectType.ENEMY);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitEnemy(this);
	}

}
