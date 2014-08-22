package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends EntityObject {

	public Enemy(final TextureRegion tr, final int texNum) {
		super(tr, texNum, EntityObjectType.ENTITY_ENEMY, WorldObjectType.ENEMY);
		collisionResolver = new CollisionResolverEnemies();
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitEnemy(this);
	}

}
