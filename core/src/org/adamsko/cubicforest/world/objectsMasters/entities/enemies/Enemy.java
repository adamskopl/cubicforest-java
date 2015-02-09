package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends CubicObject {

	public Enemy(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(tr, texNum, container, WorldObjectType.ENEMY);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitEnemy(this);
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTileEnemiesRangeValid(false);
		getTilePropertiesIndicator().setTileHeroesRangeValid(false);
	}

}
