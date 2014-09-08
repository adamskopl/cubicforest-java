package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolOrange extends HeroTool {

	public HeroToolOrange(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(tr, texNum, container, WorldObjectType.TOOLORANGE);

	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolOrange(this);
	}

}
