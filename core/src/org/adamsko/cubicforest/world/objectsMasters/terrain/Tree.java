package org.adamsko.cubicforest.world.objectsMasters.terrain;

import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;
import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tree extends CubicObject {

	public Tree(final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(objectsTextureChanger, tr, texNum, container,
				WorldObjectType.TREE);
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTileEnemiesRangeValid(false);
		getTilePropertiesIndicator().setTileHeroesRangeValid(false);
	}

}
