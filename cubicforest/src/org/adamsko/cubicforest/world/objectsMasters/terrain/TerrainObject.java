package org.adamsko.cubicforest.world.objectsMasters.terrain;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrainObject extends WorldObject {

	public TerrainObject(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container,
			final TerrainObjectType terrainType) {
		super(tr, texNum, container, WorldObjectType.OBJECT_TERRAIN,
				WorldObjectType.TREE);
	}

}
