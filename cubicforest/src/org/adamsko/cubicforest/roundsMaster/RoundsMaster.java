package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoaderCoordinator;
import org.adamsko.cubicforest.world.tile.TilePickClient;

/**
 * A round is a manager of {@link RoundPhase} objects. The round consists of
 * some number of phases, which are invoked one after another. When the last
 * phase is over, the first one begins.
 * 
 * @author adamsko
 * 
 */
public interface RoundsMaster extends TilePickClient, GuiMasterClient,
		MapsLoaderCoordinator, Nullable {

	void addPhase(final RoundPhase newPhase);

	/**
	 * All phases ended, begin new round.
	 */
	void nextRound() throws Exception;

	/**
	 * Invoked by one of the phases: information, that phase is over, has ended
	 * its work.
	 * 
	 * @param phaseEnded
	 *            phase which has ended right now
	 * @throws Exception
	 */
	void phaseIsOver(final RoundPhase phaseEnded) throws Exception;

	/**
	 * Start the next phase.
	 */
	void startNextPhase() throws Exception;

	/**
	 * Reload: reload all phases, reload all World objects.
	 */
	void reload();

	/**
	 * Rounds master holds informations about game result as a
	 * {@link GameResult} object.
	 */
	GameResult getGameResult();

	/**
	 * Changes game result. It can be changed from 'PLAY' only once until
	 * 'resetGameResult()' is not invoked. The assumption is, that game result
	 * can be changed only once, which prevents overwriting.
	 */
	void setGameResultSingle(final GameResult gameResult);

	/**
	 * Invoked when game result is read. From now game result can be changed.
	 */
	void resetGameResult();

}
