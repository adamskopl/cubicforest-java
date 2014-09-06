package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.tile.TilePickClient;

public interface RoundPhase extends TilePickClient, GuiMasterClient {

	/**
	 * Function to invoke, when phase is over. Phase should invoke 'phaseIsOver'
	 * for {@link RoundsMaster}
	 * 
	 * @param phaseOver
	 *            information if phase has passed without doing anything. needed
	 *            for monitoring how phases are acting
	 */
	void phaseIsOver(RoundPhase phaseOver) throws Exception;

	void startPhase();

	void reloadPhase();

	void setRoundsMaster(RoundsMaster roundsMaster);

	/**
	 * Check if phase has skipped last time. Needed to monitor how phases are
	 * acting (e.g. to check if phases aren't only passing creating infinite
	 * phases passing loop)
	 */
	boolean phaseSkippedLastTime();

	String getName();

}
