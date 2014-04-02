package org.adamsko.cubicforest.world.objectsMasters.terrain;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrainObject extends WorldObject {

	private TerrainObjectType_e terrainType;
	
	public TerrainObject(TextureRegion tr, int texNum, TerrainObjectType_e terrainType) {
		super(tr, texNum, WorldObjectType_e.OBJECT_TERRAIN);
		this.terrainType = terrainType;
	}

}
