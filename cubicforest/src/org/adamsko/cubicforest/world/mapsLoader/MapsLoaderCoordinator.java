package org.adamsko.cubicforest.world.mapsLoader;

/**
 * Interface for class using {@link MapsLoader}
 */
public interface MapsLoaderCoordinator {

	void setMapActive(final int activeMapIndex);

	void reloadWorldMapsLoader();

}
