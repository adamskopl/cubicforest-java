package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolTurret extends CubicHeroTool {

	public HeroToolTurret(final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container) {
		super(objectsTextureChanger, tr, texNum, container,
				WorldObjectType.TOOLTURRET);

	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolTurret(this);
	}

}
