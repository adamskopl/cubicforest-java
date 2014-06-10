package org.adamsko.cubicforest.world.mapsLoader;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.converter.TiledCfConverter;
import org.adamsko.cubicforest.world.mapsLoader.converter.TiledObjectType_e;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledLayer;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.Gson;

public class MapsLoader {

	TiledMap tiledMap;
	TiledCfConverter tiledCfConverter;
	
	public MapsLoader() {
		loadTestMap();
		tiledCfConverter = new TiledCfConverter(tiledMap);
	}

	private void loadTestMap() {

		Boolean exists = new Boolean(Gdx.files.internal("data/maps/clearMap.json")
				.exists());

		FileHandle mapFile = Gdx.files.internal("data/maps/clearMap.json");

		String mapFileString = mapFile.readString();
		
		tiledMap = new Gson().fromJson(mapFileString, TiledMap.class);
		List <TiledLayer> layersList = tiledMap.getLayers();

	}
	
	public List<Vector2> getCoords(TiledObjectType_e objectType) {
		return tiledCfConverter.getCoords(objectType);
	}
	
}
