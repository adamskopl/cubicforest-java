package org.adamsko.cubicforest.world.tilePathsMaster.searcher;

import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameterFactory;

/**
 * Interface for class managing {@link TilePathSearcher} implementations.
 * 
 * @author adamsko
 * 
 */
public interface TilePathSearchersMaster {

	TilesSearchParameterFactory getTilesSearchParameterFactory();

	TilePathSearcher getTilePathSearcherValidPath();

	TilePathSearcher getTilePathSearcherNearestTile();

}
