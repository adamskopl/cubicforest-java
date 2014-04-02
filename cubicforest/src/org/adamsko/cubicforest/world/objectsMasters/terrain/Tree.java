package org.adamsko.cubicforest.world.objectsMasters.terrain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tree extends TerrainObject {

	public Tree(TextureRegion tr, int texNum) {
		super(tr, texNum, TerrainObjectType_e.TERRAIN_TREE);
	}

}
