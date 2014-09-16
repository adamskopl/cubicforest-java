package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolPortal extends CubicHeroTool {

	public HeroToolPortal(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container,
			final HeroesMaster heroesMaster) {
		super(tr, texNum, container, WorldObjectType.TOOLPORTAL);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolPortal(this);
	}

}
