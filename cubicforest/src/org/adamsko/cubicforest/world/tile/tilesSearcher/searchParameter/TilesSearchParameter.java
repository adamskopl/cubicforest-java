package org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;

/**
 * Interface for classes describing what kind of tiles are searched with tile
 * search methods. E.g. tiles with enemies should be excluded from search.
 * 
 * @author adamsko
 * 
 */
public interface TilesSearchParameter extends Nullable {

	/**
	 * TODO: description
	 * 
	 * Check if tile's properties described by {@link TilePropertiesIndicator}
	 * are valid for this search parameter
	 * 
	 * @param propertiesIndicator
	 * @return
	 */
	boolean tilePropertiesValid(TilePropertiesIndicator propertiesIndicator);

}
