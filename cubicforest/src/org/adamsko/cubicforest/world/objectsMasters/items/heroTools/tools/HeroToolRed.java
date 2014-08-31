package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolRed extends HeroTool {

	public HeroToolRed(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(tr, texNum, container, WorldObjectType.TOOLRED);

	}

}
