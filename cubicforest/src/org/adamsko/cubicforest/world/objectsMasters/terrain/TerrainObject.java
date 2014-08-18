package org.adamsko.cubicforest.world.objectsMasters.terrain;

import org.adamsko.cubicforest.world.object.Type;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrainObject extends WorldObject {

	public TerrainObject(final TextureRegion tr, final int texNum,
			final TerrainObjectType terrainType) {
		super(tr, texNum, Type.OBJECT_TERRAIN);
	}

}
