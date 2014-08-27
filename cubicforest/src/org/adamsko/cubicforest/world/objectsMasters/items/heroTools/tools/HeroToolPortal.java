package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolPortal extends HeroTool {

	public HeroToolPortal(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container,
			final HeroesMaster heroesMaster) {
		super(tr, texNum, container, HeroToolType.TOOL_PORTAL);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolPortal(this);
	}

}
