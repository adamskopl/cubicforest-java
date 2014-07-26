package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MapsLoaderTiled implements MapsLoader {

	private List<TiledMap> maps;
	private int activeMapIndex = 0;
	// file handle for folder containing maps
	private FileHandle mapsFolder;

	public MapsLoaderTiled() {
		maps = new ArrayList<TiledMap>();
	}
	
	@Override
	public void loadMaps() {
		mapsFolder = getMapsFolderHandle();
		if (!mapsFolder.exists()) {
			return;
		}
		reloadMaps();
	}

	@Override
	public void reloadMaps() {
		maps.clear();
		for (FileHandle entry : mapsFolder.list()) {
			String mapFileString = entry.readString();
			TiledMap newMap;
			try {
				newMap = new Gson().fromJson(mapFileString, TiledMap.class);
			} catch (JsonSyntaxException ex) {
				Gdx.app.error(entry.toString(), ex.toString());
				continue;
			}
			newMap.initConverter();
			maps.add(newMap);
			Gdx.app.error(entry.toString(), "OK");
		}
	}

	@Override
	public int size() {
		return maps.size();
	}

	@Override
	public void setMapActive(int mapIndex) {
		if(mapIndex > size()) {
			Gdx.app.error(toString(), "mapIndex > size()");
			return;
		}
		activeMapIndex = mapIndex;
	}

	@Override
	public CFMap getMapActive() {
		return maps.get(activeMapIndex);
	}
	
	@Override
	public int getMapActiveIndex() {
		return activeMapIndex;
	}

	/**
	 * Return file handle of not empty maps folder.
	 * 
	 * @return
	 */
	private FileHandle getMapsFolderHandle() {

		List<String> mFoldersList = new ArrayList<String>();
		// path for users testing executable .jar file
		mFoldersList.add("./userMaps");
		// Desktop path
		mFoldersList.add("./bin/data/maps");
		// Android path
		mFoldersList.add("data/maps");

		for (String path : mFoldersList) {
			FileHandle handle = Gdx.files.internal(path);
			if (handle.exists() && handle.list().length != 0) {
				return Gdx.files.internal(path);
			}
		}

		Gdx.app.error("getMapsFolderHandle()", "no maps folder");
		return new FileHandle("");
	}

}
