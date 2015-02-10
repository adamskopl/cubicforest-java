package org.adamsko.cubicforest.world.objectsMasters.items.prizes;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.gui.prizes.GuiPrizes;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;

public interface PrizesMaster extends Nullable, WorldObjectsMaster,
		RenderableObjectsMaster {

	/**
	 * Get {@link GuiMaster} for prizes gui manipulation
	 */
	GuiPrizes getGuiPrizes();

	/**
	 * Change prize's texture.
	 * 
	 * @param prize
	 *            prize to be changed
	 */
	public void prizeHighlight(final Prize prize);

	/**
	 * Change cube's texture to its original one
	 * 
	 * @param prize
	 *            prize to be changed
	 */
	public void prizeUnHighlight(final Prize prize);

	/**
	 * Invoked when hero has collected prize. Update: add new gui element.
	 */
	void onPrizeCollected();

	/**
	 * How many prizes player has collected?
	 */
	int prizesCollected();

	/**
	 * Check if all prizes are collected;
	 * 
	 * @return true if all prizes are collected
	 */
	boolean allPrizesCollected();

}
