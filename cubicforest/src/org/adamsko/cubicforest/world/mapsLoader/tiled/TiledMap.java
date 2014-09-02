package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * FIXME: develop {@link CFMap} interface
 * 
 * Class representing JSON format of Tiled map.
 * 
 * @author adamsko
 * 
 */
public class TiledMap implements CFMap {

	private int width;
	private int height;
	private int tileheight;
	private int tilewidth;
	private List<TiledLayer> layers;

	TiledCfConverter tiledCfConverter;

	public TiledMap() {
		tiledCfConverter = new TiledCfConverter(this);
	}

	// < GETTERS SETTERS
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(final int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(final int height) {
		this.height = height;
	}

	@Override
	public int getTileheight() {
		return tileheight;
	}

	@Override
	public void setTileheight(final int tileheight) {
		this.tileheight = tileheight;
	}

	@Override
	public int getTilewidth() {
		return tilewidth;
	}

	@Override
	public void setTilewidth(final int tilewidth) {
		this.tilewidth = tilewidth;
	}

	@Override
	public List<TiledLayer> getLayers() {
		return layers;
	}

	@Override
	public void setLayers(final List<TiledLayer> layers) {
		this.layers = layers;
	}

	// GETTERS SETTERS >

	@Override
	public List<TiledObject> getObjectsFromLayer(
			final TiledObjectType layerObjectType) {
		final TiledLayer layer = getLayer(layerObjectType.toString());
		if (layer != null) {
			return layer.getObjects();
		}

		return null;
	}

	@Override
	public List<Vector2> getObjectTypeCoords(final TiledObjectType objectType) {
		return tiledCfConverter.getObjectTypeCoords(objectType);
	}

	/**
	 * After successful load, initialize {@link TiledCfConverter} object.
	 */
	void initConverter() {
		tiledCfConverter.loadTiledObjects();
	}

	public void printLayers() {
		for (final TiledLayer tl : getLayers()) {
			Gdx.app.debug("layer ", tl.getName());
		}
	}

	private TiledLayer getLayer(final String layerName) {
		for (final TiledLayer tl : layers) {
			if (tl.getName().equals(layerName)) {
				return tl;
			}
		}
		Gdx.app.error("getLayer " + layerName, "no layer");
		return null;
	}

}