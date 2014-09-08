package org.adamsko.cubicforest.world.tile.propertiesIndicator;

import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;

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
	boolean getTilePathSearchValid();

	/**
	 * Set value for getTilePathSearchValid().
	 */
	void setTilePathSearchValid(boolean tilePathSearchValid);

	/**
	 * Returns information, if related tile should be highlighted as being
	 * occupied (not accessible to heroes).
	 */
	boolean getTileHighlightedAsOccupied();

	void setTileHighlightedAsOccupied(boolean tileHighlightOccupied);

	/**
	 * Returns information, if related tile should be considered as a tile that
	 * is in range of the {@link Enemy} (can be reached by enemy and enemy can
	 * walk on such tile)
	 */
	boolean getTileEnemiesRangeValid();

	/**
	 * Set value for {@link #getTileEnemiesRangeValid()}
	 */
	void setTileEnemiesRangeValid(boolean tileEnemiesRangeValid);

	/**
	 * Returns information, if related tile should be considered as a tile that
	 * is in range of the {@link Hero} (can be reached by enemy and enemy can
	 * walk on such tile)
	 */
	boolean getTileHeroesRangeValid();

	/**
	 * Set value for {@link #getTileHeroesRangeValid()}
	 */
	void setTileHeroesRangeValid(boolean tileHeroesRangeValid);

}
