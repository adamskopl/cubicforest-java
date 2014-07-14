package org.adamsko.cubicforest.world.mapsLoader.converter;

import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObject;
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
	HashMap<TiledObjectType_e, List<TiledObject>> tiledObjectsMap;

	public TiledCfConverter(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
		loadTiledObjects();
	}

	private void loadTiledObjects() {
		tiledObjectsMap = new HashMap<TiledObjectType_e, List<TiledObject>>();

		for (TiledObjectType_e tiledType : TiledObjectType_e.values()) {
			List<TiledObject> tiledObjects;
			tiledObjects = tiledMap.getObjectsFromLayer(tiledType);
			if (tiledObjects == null) {
				Gdx.app.error("loadTiledObjects() " + tiledType.toString(),
						"no objects");
			}

			tiledObjectsMap.put(tiledType, tiledObjects);
		}
	}

	private List<Vector2> getCoords(List<TiledObject> tiledObjects) {
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

	public List<Vector2> getCoords(TiledObjectType_e objectType) {
		List<TiledObject> tiledObjects = tiledObjectsMap.get(objectType);
		if (tiledObjects == null) {
			Gdx.app.error("getCoords " + objectType.toString(), "not in a map");
		}

		return getCoords(tiledObjectsMap.get(objectType));
	}

}
