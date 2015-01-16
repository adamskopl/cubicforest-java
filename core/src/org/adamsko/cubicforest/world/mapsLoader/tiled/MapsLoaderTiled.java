package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.tile.TilesContainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MapsLoaderTiled implements MapsLoader {

	private List<TiledMap> maps;
	private int activeMapIndex = 0;
	// file handle for folder containing maps
	private FileHandle mapsFolder;

	private final WorldObjectsMastersContainer worldObjectsMastersContainer;
	private final GuiMaster guiMaster;

	MapsLoaderTiled(final boolean nullConstructor) {
		this.worldObjectsMastersContainer = null;
		this.guiMaster = null;
	}

	public MapsLoaderTiled(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final GuiMaster guiMaster) {

		this.worldObjectsMastersContainer = worldObjectsMastersContainer;
		this.guiMaster = guiMaster;

		maps = new ArrayList<TiledMap>();
	}

	@Override
	public boolean isNull() {
		return false;
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
		for (final FileHandle entry : mapsFolder.list()) {
			final String mapFileString = entry.readString();
			TiledMap newMap;
			try {
				newMap = new Gson().fromJson(mapFileString, TiledMap.class);
			} catch (final JsonSyntaxException ex) {
				Gdx.app.error(entry.toString(), ex.toString());
				continue;
			}
			newMap.initConverter();
			maps.add(newMap);

			// uncomment to display information about loaded maps
			// Gdx.app.debug(entry.toString(), "OK");
		}
	}

	@Override
	public void reloadWorld() {
		reloadMaps();

		final List<WorldObjectsMaster> worldObjectsMasters = worldObjectsMastersContainer
				.getWorldObjectsMasters();
		final List<GuiElementsContainer> guiContainers = guiMaster.getGuiList();

		worldObjectsMastersContainer.unloadMasters();

		worldObjectsMastersContainer.loadMasters(getMapActive());

		for (final GuiElementsContainer guiContainer : guiContainers) {
			guiContainer.reload(getMapActive());
		}

		// uncomment to print tiles occupants
		final TilesContainer tc = (TilesContainer) worldObjectsMasters.get(0);
		tc.debugPrintOccupants(false);

	}

	@Override
	public int size() {
		return maps.size();
	}

	@Override
	public void setMapActive(final int mapIndex) {
		if (mapIndex > size()) {
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
	 */
	private FileHandle getMapsFolderHandle() {

		final List<String> mFoldersList = new ArrayList<String>();
		// path for users testing executable .jar file
		mFoldersList.add("./userMaps");
		// Desktop path
		mFoldersList.add("./bin/data/maps");
		// Android path
		mFoldersList.add("data/maps");

		for (final String path : mFoldersList) {
			final FileHandle handle = Gdx.files.internal(path);
			if (handle.exists() && handle.list().length != 0) {
				return Gdx.files.internal(path);
			}
		}

		Gdx.app.error("getMapsFolderHandle()", "no maps folder");
		return new FileHandle("");
	}

}
