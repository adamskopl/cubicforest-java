package org.adamsko.cubicforest.world.tile.propertiesIndicator;

/**
 * Returns informations needed for algorithms connected with Tiles. In this way,
 * class containing {@link TilePropertiesIndicator} can define how it affects
 * tiles.
 * 
 * @author adamsko
 * 
 */
public interface TilePropertiesIndicator {

	/**
	 * Returns information, if related tile is valid for tile path search
	 * algorithms (if it can be included in tile paths).
	 */
	boolean isTilePathSearchValid();

	/**
	 * Returns information, if related tile should be highlighted as being
	 * occupied (not accessible to heroes).
	 */
	boolean isTileHighlightedAsOccupied();

	/**
	 * Set information, for getTilePathSearchValid().
	 */
	void setTilePathSearchValid(boolean tilePathSearchValid);

}
