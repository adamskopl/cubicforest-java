package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.players.resolver.MapsResolverClient;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
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
		MapsLoaderCoordinator, MapsResolverClient, Nullable {

	void addPhase(final RoundPhase newPhase);

	void addPhase(PhaseHeroes phaseHeroes);

	void addPhase(PhaseEnemies phaseEnemies);

	PhaseHeroes getPhaseHeroes();

	PhaseEnemies getPhaseEnemies();

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

	public GameResultMaster getGameResultMaster();

}
