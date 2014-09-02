package org.adamsko.cubicforest.world.mapsLoader;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledLayer;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObject;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.math.Vector2;
import com.google.gson.Gson;

/**
 * Interface for a class representing game's level. It should represent the
 * structure of Json exported by https://github.com/bjorn/tiled editor. Check
 * any Cubicforest level in 'maps' folder for details.
 * 
 * @author adamsko
 * 
 */
public interface CFMap {

	/**
	 * Get list of {@link Tile} coordinations of given Tile objects type. Allows
	 * to find coordinates of all objects of given type on a map.
	 * 
	 * @param objectType
	 * @return
	 */
	List<Vector2> getObjectTypeCoords(TiledObjectType objectType);

	/**
	 * Searches for a layer corresponding to given {@link TiledObjectType} and
	 * returns all {@link TiledObject} objects representing objects that will be
	 * loaded in game.
	 * 
	 * @param layerObjectType
	 *            indicates layer from which objects should be returned
	 * @return
	 */
	List<TiledObject> getObjectsFromLayer(final TiledObjectType layerObjectType);

	/**
	 * get map's width (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	int getWidth();

	/**
	 * set map's width (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	void setWidth(final int width);

	/**
	 * get map's height (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	int getHeight();

	/**
	 * set map's height (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	void setHeight(final int height);

	/**
	 * get map's tile height (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	int getTileheight();

	/**
	 * set map's tile height (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	void setTileheight(final int tileheight);

	/**
	 * get map's tile width (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	int getTilewidth();

	/**
	 * set map's tile width (from Tiled format). used by {@link Gson}
	 * 
	 * @return
	 */
	void setTilewidth(final int tilewidth);

	/**
	 * get layers Tile format layers represented by {@link TiledLayer} format.
	 * used by {@link Gson}
	 * 
	 * @return
	 */
	List<TiledLayer> getLayers();

	void setLayers(final List<TiledLayer> layers);

}
