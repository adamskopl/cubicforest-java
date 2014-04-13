package org.adamsko.cubicforest.world.mapsLoader.tiled;

import java.util.List;


public class TiledLayer {

	private String name;	
	private Integer height;
	List<TiledObject> objects;
	
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TiledObject> getObjects() {
		return objects;
	}
	public void setObjects(List<TiledObject> objects) {
		this.objects = objects;
	}

	
}
