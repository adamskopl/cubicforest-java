package org.adamsko.cubicforest.world.mapsLoader.tiled;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Converts Tiled JSON to CF objects.
 * 
 * @author adamsko
 * 
 */
public class TiledCfConverter {

	private TiledMap tiledMap;
	HashMap<TiledObjectType, List<TiledObject>> tiledObjectsMap;

	public TiledCfConverter(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	/**
	 * Load TileObject object to tiledObjectMap map.
	 * Use after tiledMap is loaded.
	 */
	void loadTiledObjects() {
		tiledObjectsMap = new HashMap<TiledObjectType, List<TiledObject>>();

		for (TiledObjectType tiledType : TiledObjectType.values()) {
			List<TiledObject> tiledObjects;
			tiledObjects = tiledMap.getObjectsFromLayer(tiledType);
			if (tiledObjects == null) {
				Gdx.app.error("loadTiledObjects() " + tiledType.toString(),
						"no objects");
			}

			tiledObjectsMap.put(tiledType, tiledObjects);
		}
	}

	private List<Vector2> getObjectsCoords(List<TiledObject> tiledObjects) {
		List<Vector2> coords = new ArrayList<Vector2>();
		if (tiledObjects == null) {
			Gdx.app.error("getCoords", "tiledObjects == null");
			return coords;
		}

		for (TiledObject to : tiledObjects) {
			Vector2 coord = new Vector2();
			coord.set(to.getX() / tiledMap.getTilewidth(),
					to.getY() / tiledMap.getTileheight());

			// TEMPORARY SOLUTION: center coordinates
			coord.add(7, -3);

			coords.add(coord);
		}
		return coords;
	}

	List<Vector2> getObjectTypeCoords(TiledObjectType objectType) {
		List<TiledObject> tiledObjects = tiledObjectsMap.get(objectType);
		if (tiledObjects == null) {
			Gdx.app.error("getCoords " + objectType.toString(), "not in a map");
		}

		return getObjectsCoords(tiledObjectsMap.get(objectType));
	}

}
