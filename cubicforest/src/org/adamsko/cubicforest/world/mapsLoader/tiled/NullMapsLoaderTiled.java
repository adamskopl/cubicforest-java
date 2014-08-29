package org.adamsko.cubicforest.world.mapsLoader.tiled;


public class NullMapsLoaderTiled extends MapsLoaderTiled {
	private static NullMapsLoaderTiled instance = null;

	private NullMapsLoaderTiled() {
		super(false);
	}

	public static NullMapsLoaderTiled instance() {
		if (instance == null) {
			instance = new NullMapsLoaderTiled();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
