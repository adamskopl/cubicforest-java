package org.adamsko.cubicforest.world.tilePathsMaster.searcher;


/**
 * Interface for class managing {@link TilePathSearcher} implementations.
 * 
 * @author adamsko
 * 
 */
public interface TilePathSearchersMaster {

	TilePathSearcher getTilePathSearcherValidPath();

	TilePathSearcher getTilePathSearcherNearestTile();

}
