package org.adamsko.cubicforest.world;

/**
 * Interface for a class responsible for managing all game managers/masters.
 * Initializes game and its objects and letting other managers to do the work.
 * 
 * @author adamsko
 * 
 */
public interface GameWorldBuilder {

	void update(final float deltaTime);

}
