package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

public class HeroToolTrap extends CubicHeroTool {

	public HeroToolTrap(final WorldObjectsMaster container) {
		super(container, WorldObjectType.TOOLTRAP);
		CLog.addObject(this, "HeroToolTrap");
		CLog.setUsage(this, true);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolTrap(this);
	}

}
