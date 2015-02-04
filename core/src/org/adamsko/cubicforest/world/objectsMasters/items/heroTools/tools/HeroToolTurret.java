package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTurret extends CubicHeroTool {

	public HeroToolTurret(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container) {
		super(tr, texNum, container, WorldObjectType.TOOLTURRET);

	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolTurret(this);
	}

}
