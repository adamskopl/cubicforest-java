package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.math.Vector2;

/**
 * Extracts coordinates of {@link WorldObject} objects from {@link TiledMap}
 * based on {@link TiledObjectType}
 */
public interface TiledCfConverter {

	/**
	 * Using {@link TiledMap} load {@link WorldObject} objects, to be ready on
	 * {@link #getObjectTypeCoords(TiledObjectType)}.
	 */
	void loadTiledObjects(final TiledMap tiledMap);

	/**
	 * Based on {@link TiledObjectType} return tile coordinates of objects
	 */
	List<Vector2> getObjectTypeCoords(final TiledObjectType objectType);

}
