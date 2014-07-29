package org.adamsko.cubicforest.world.objectsMasters.terrain;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrainObject extends WorldObject {

	public TerrainObject(TextureRegion tr, int texNum, TerrainObjectType terrainType) {
		super(tr, texNum, WorldObjectType.OBJECT_TERRAIN);
	}

}
