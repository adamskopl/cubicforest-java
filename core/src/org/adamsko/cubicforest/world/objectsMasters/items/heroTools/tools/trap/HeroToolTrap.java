package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTrap extends CubicHeroTool {

	public HeroToolTrap(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container) {
		super(tr, texNum, container, WorldObjectType.TOOLTRAP);
		ConditionalLog.addObject(this, "HeroToolTrap");
		ConditionalLog.setUsage(this, true);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolTrap(this);
	}

}
