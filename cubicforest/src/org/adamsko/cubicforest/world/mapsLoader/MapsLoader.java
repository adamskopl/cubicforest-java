package org.adamsko.cubicforest.world.mapsLoader;

import org.adamsko.cubicforest.Nullable;

public interface MapsLoader extends Nullable {

	/**
	 * Load all maps.
	 */
	public void loadMaps();

	/**
	 * Load all maps after first 'loadMaps()'
	 */
	public void reloadMaps();

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
