package org.adamsko.cubicforest.gui.resolver;

import org.adamsko.cubicforest.gui.GuiElementsContainer;

public interface GuiResolver extends GuiElementsContainer {

	/**
	 * New solution: add gui element.
	 */
	void addNewSolution();

	/**
	 * Invoked when new resolve has started. Clear all gui elements, prepare for
	 * adding new gui elements
	 */
	void newResolveStarted();

}
