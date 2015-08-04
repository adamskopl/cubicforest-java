package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

public class HeroToolOrange extends CubicHeroTool {

	public HeroToolOrange(final WorldObjectsMaster container) {
		super(container, WorldObjectType.TOOLORANGE);

	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolOrange(this);
	}

}
