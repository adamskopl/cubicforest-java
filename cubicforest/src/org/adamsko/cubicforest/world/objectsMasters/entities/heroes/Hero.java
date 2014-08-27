package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends WorldObject {

	public Hero(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container) {
		super(tr, texNum, container, WorldObjectType.HERO);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitHero(this);
	}

}
