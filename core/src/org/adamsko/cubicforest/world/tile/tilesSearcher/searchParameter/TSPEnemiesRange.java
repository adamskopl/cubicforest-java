package org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter;

import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;

public class TSPEnemiesRange implements TilesSearchParameter {

	@Override
	public boolean tilePropertiesValid(
			final TilePropertiesIndicator propertiesIndicator) {
		return propertiesIndicator.getTileEnemiesRangeValid();
	}

	@Override
	public boolean isNull() {
		return false;
	}

}
