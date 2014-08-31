package org.adamsko.cubicforest.world.objectsMasters.terrain;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tree extends WorldObject {

	public Tree(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(tr, texNum, container, WorldObjectType.TREE);
	}

}
