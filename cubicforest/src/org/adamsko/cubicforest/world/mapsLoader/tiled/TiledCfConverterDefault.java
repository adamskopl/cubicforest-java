package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TiledCfConverterDefault implements TiledCfConverter {

	HashMap<TiledObjectType, List<TiledObject>> tiledObjectsMap;

	private int tiledTileWidth, tiledTileHeight;

	/**
	 * Load TileObject object to tiledObjectMap map. Use after tiledMap is
	 * loaded.
	 */
	@Override
	public void loadTiledObjects(final TiledMap tiledMap) {
		tiledObjectsMap = new HashMap<TiledObjectType, List<TiledObject>>();

		for (final TiledObjectType tiledType : TiledObjectType.values()) {
			List<TiledObject> tiledObjects;
			tiledObjects = tiledMap.getObjectsFromLayer(tiledType);
			if (tiledObjects == null) {
				Gdx.app.error("loadTiledObjects() " + tiledType.toString(),
						"no objects");
			}

			tiledObjectsMap.put(tiledType, tiledObjects);
		}
		tiledTileWidth = tiledMap.getTilewidth();
		tiledTileHeight = tiledMap.getTileheight();
	}

	@Override
	public List<Vector2> getObjectTypeCoords(final TiledObjectType objectType) {
		return getObjectsCoords(tiledObjectsMap.get(objectType));
	}

	private List<Vector2> getObjectsCoords(final List<TiledObject> tiledObjects) {
		final List<Vector2> coords = new ArrayList<Vector2>();
		if (tiledObjects == null) {
			Gdx.app.error("getCoords", "tiledObjects == null");
			return coords;
		}

		for (final TiledObject to : tiledObjects) {
			final Vector2 coord = new Vector2();
			coord.set(to.getX() / tiledTileWidth, to.getY() / tiledTileHeight);

			// TEMPORARY SOLUTION: center coordinates
			coord.add(7, -3);

			coords.add(coord);
		}
		return coords;
	}

}
