package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

public class HeroToolTurret extends CubicHeroTool {

	public HeroToolTurret(final WorldObjectsMaster container) {
		super(container, WorldObjectType.TOOLTURRET);

	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolTurret(this);
	}

}
