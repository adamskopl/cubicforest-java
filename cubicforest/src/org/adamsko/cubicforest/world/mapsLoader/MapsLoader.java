package org.adamsko.cubicforest.world.mapsLoader;

public interface MapsLoader {

	/**
	 * Load all maps.
	 */
	public void loadMaps();

	public int size();

	/**
	 * Set active map. This map will be used by WorldObjectsMaster objects.
	 * 
	 * @param mapIndex
	 */
	public void setMapActive(int mapIndex);

	/**
	 * Get active map.
	 * 
	 * @return active map
	 */
	public CFMap getMapActive();
	
	public int getMapActiveIndex();

}
