package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTrap extends HeroTool {

	public HeroToolTrap(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container) {
		super(tr, texNum, container, WorldObjectType.TOOLTRAP);

	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolTrap(this);
	}

}
