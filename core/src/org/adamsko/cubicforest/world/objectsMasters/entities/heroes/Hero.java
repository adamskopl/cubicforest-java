package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;
import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends CubicObject {

	public Hero(final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(objectsTextureChanger, tr, texNum, container,
				WorldObjectType.HERO);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitHero(this);
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		// heroes tiles can be reached by enemies
		getTilePropertiesIndicator().setTileEnemiesRangeValid(true);
		getTilePropertiesIndicator().setTileHeroesRangeValid(false);
	}
}
