package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;

public class Enemy extends CubicObject {

	public Enemy(final WorldObjectsMasterDefault container) {
		super(container, WorldObjectType.ENEMY);
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
