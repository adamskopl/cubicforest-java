package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolOrange extends CubicHeroTool {

	public HeroToolOrange(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container) {
		super(tr, texNum, container, WorldObjectType.TOOLORANGE);

	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolOrange(this);
	}

}
