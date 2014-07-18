package org.adamsko.cubicforest.world.mapsLoader;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;

import com.badlogic.gdx.math.Vector2;

public interface CFMap {

	
	/**
	 * Get list of tile coordination of given Tile objects type.
	 * 
	 * @param objectType
	 * @return
	 */
	public List<Vector2> getObjectTypeCoords(TiledObjectType objectType);
	
}
