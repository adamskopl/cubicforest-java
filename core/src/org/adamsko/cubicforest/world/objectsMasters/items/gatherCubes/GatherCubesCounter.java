package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.render.world.object.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;

/**
 * Holds data about {@link GatherCube} collected by heroes. FIXME: for now also
 * acts as a GUI element representing cubes number
 * 
 * @author adamsko
 */
public interface GatherCubesCounter extends RenderableObjectsMaster,
		GuiElementsContainer {

	/**
	 * Set starting counter value.
	 */
	void setStartingValue(int startingValue);

	/**
	 * Restore starting value.
	 */
	void reset();

	/**
	 * Get current number of gathered cubes
	 */
	int getCounter();

	void setCounter(int counter);

	/**
	 * Increase gathered cubes number by given value
	 */
	void addValue(final int value);

	/**
	 * Check if counter allows heroTool to be build (if player can afford it).
	 */
	Boolean isToolAffordable(final WorldObjectType heroToolType);

	@Override
	String toString();

}
