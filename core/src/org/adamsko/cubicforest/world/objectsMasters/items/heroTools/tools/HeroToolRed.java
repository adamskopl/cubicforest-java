package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolRed extends CubicHeroTool {

	public HeroToolRed(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container) {
		super(tr, texNum, container, WorldObjectType.TOOLRED);

	}

}
