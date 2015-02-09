package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.exit;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolExit extends CubicHeroTool {

	public HeroToolExit(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container) {
		super(tr, texNum, container, WorldObjectType.TOOLEXIT);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolExit(this);
	}

}
