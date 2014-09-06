package org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter;

import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;

public class TSPHeroesRange implements TilesSearchParameter {

	@Override
	public boolean tilePropertiesValid(
			final TilePropertiesIndicator propertiesIndicator) {
		return propertiesIndicator.getTileHeroesRangeValid();
	}

	@Override
	public boolean isNull() {
		return false;
	}

}
