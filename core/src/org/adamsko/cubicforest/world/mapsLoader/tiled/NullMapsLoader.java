package org.adamsko.cubicforest.world.mapsLoader.tiled;


public class NullMapsLoader extends MapsLoaderTiled {
	private static NullMapsLoader instance = null;

	private NullMapsLoader() {
		super(false);
	}

	public static NullMapsLoader instance() {
		if (instance == null) {
			instance = new NullMapsLoader();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
