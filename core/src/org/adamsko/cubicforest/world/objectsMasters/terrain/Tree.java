package org.adamsko.cubicforest.world.objectsMasters.terrain;

import org.adamsko.cubicforest.render.cubicModel.CubicModelDefault;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;

public class Tree extends CubicModelDefault {

	public Tree(final WorldObjectsMasterDefault container) {
		super(container, WorldObjectType.TREE);
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTileEnemiesRangeValid(false);
		getTilePropertiesIndicator().setTileHeroesRangeValid(false);
	}

}
