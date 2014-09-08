package org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter;

import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;

public class NullTSP implements TilesSearchParameter {
	private static NullTSP instance = null;

	private NullTSP() {
		super();
	}

	public static NullTSP instance() {
		if (instance == null) {
			instance = new NullTSP();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public boolean tilePropertiesValid(
			final TilePropertiesIndicator propertiesIndicator) {
		return false;
	}
}
