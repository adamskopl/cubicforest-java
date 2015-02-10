package org.adamsko.cubicforest.gui.prizes;

import org.adamsko.cubicforest.gui.GuiElementsContainer;

public interface GuiPrizes extends GuiElementsContainer {

	/**
	 * Invoked when hero has collected prize. Update: add new gui element.
	 */
	void prizeCollected();

	/**
	 * Set prizes gui elements number.
	 */
	void setCollectedPrizesNumber(int prizesCollected);

}
