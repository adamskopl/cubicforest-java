package org.adamsko.cubicforest.world.mapsLoader;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.WorldObjectsMaster;

public interface MapsLoader extends Nullable {

	/**
	 * Load all maps.
	 */
	public void loadMaps();

	/**
	 * Load all maps after first 'loadMaps()'
	 */
	public void reloadMaps();

	/**
	 * Unload (clear), then load objects from {@link WorldObjectsMaster} objects
	 * again to their original positions. Objects from particular
	 * {@link WorldObjectsMaster} objects, should not be loaded before all other
	 * Masters unload their objects.
	 */
	public void reloadWorld();

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
