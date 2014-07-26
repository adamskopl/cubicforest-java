package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Class representing JSON format of Tiled map.
 * 
 * @author adamsko
 * 
 */
public class TiledMap implements CFMap {

	private Integer width;
	private Integer height;
	private Integer tileheight;
	private Integer tilewidth;
	private List<TiledLayer> layers;

	TiledCfConverter tiledCfConverter;

	public TiledMap() {
		tiledCfConverter = new TiledCfConverter(this);
	}

	// < GETTERS SETTERS
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getTileheight() {
		return tileheight;
	}

	public void setTileheight(Integer tileheight) {
		this.tileheight = tileheight;
	}

	public Integer getTilewidth() {
		return tilewidth;
	}

	public void setTilewidth(Integer tilewidth) {
		this.tilewidth = tilewidth;
	}

	public List<TiledLayer> getLayers() {
		return layers;
	}

	public void setLayers(List<TiledLayer> layers) {
		this.layers = layers;
	}

	/**
	 * After successful load, initialize {@link TiledCfConverter} object.
	 */
	void initConverter() {
		tiledCfConverter.loadTiledObjects();
	}

	// GETTERS SETTERS >

	public void printLayers() {
		for (TiledLayer tl : getLayers()) {
			Gdx.app.debug("layer ", tl.getName());
		}
	}

	public TiledLayer getLayer(String layerName) {
		for (TiledLayer tl : layers) {
			if (tl.getName().equals(layerName))
				return tl;
		}
		Gdx.app.error("getLayer " + layerName, "no layer");
		return null;
	}

	List<TiledObject> getObjectsFromLayer(TiledObjectType layerObjectType) {
		TiledLayer layer = getLayer(layerObjectType.toString());
		if (layer != null)
			return layer.getObjects();

		return null;
	}

	@Override
	public List<Vector2> getObjectTypeCoords(TiledObjectType objectType) {
		return tiledCfConverter.getObjectTypeCoords(objectType);
	}

}