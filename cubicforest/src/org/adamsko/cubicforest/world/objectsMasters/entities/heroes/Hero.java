package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends EntityObject {

	public Hero(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container) {
		super(tr, texNum, container, EntityObjectType.ENTITY_HERO,
				WorldObjectType.HERO);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitHero(this);
	}

}
