package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.exit;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

public class HeroToolExit extends CubicHeroTool {

	public HeroToolExit(final WorldObjectsMaster container) {
		super(container, WorldObjectType.TOOLEXIT);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolExit(this);
	}

}
