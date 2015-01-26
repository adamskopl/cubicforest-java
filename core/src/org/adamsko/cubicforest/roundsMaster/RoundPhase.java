package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.players.Player;
import org.adamsko.cubicforest.players.PlayerActionsHandler;

/**
 * Component of {@link RoundsMaster}. Concrete phases should provide methods
 * needed by concrete {@link PlayerActionsHandlerPhase} classes.
 */
public interface RoundPhase {

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

	/**
	 * Set player, which is controlling game.
	 */
	void setActivePlayer(Player activePlayer);

	/**
	 * Phase should handle player's actions by giving it's
	 * {@link PlayerActionsHandler}.
	 */
	PlayerActionsHandler getPlayerActionsHandler();

	/**
	 * Initialize {@link PlayerActionsHandler} exposed by
	 * {@link #getPlayerActionsHandler()}.
	 */
	void initPlayerActionsHandler();

}
