package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.converter.TiledObjectType_e;

import com.badlogic.gdx.Gdx;


/**
 * Class representing JSON format of Tiled map.
 * 
 * @author adamsko
 *
 */
public class TiledMap {
	
	private Integer width;
	private Integer height;
	private Integer tileheight;
	private Integer tilewidth;
	private List<TiledLayer> layers;
	
	
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
	// GETTERS SETTERS >
	
	public void printLayers() {
		for(TiledLayer tl : getLayers()) {
			Gdx.app.debug("layer ", tl.getName());
		}
	}
	
	public TiledLayer getLayer(String layerName) {
		for(TiledLayer tl : layers) {
			if(tl.getName().equals(layerName))
				return tl;
		}
		Gdx.app.error("getLayer " + layerName, "no layer");
		return null;
	}
	
	public List<TiledObject> getObjectsFromLayer(TiledObjectType_e objectType) {
		TiledLayer layer = getLayer(objectType.toString());
		if(layer != null)
			return layer.getObjects();
		
		return null;
	}
}