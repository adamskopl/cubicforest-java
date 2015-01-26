package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoaderCoordinator;

/**
 * A round is a manager of {@link RoundPhase} objects. The round consists of
 * some number of phases, which are invoked one after another. When the last
 * phase is over, the first one begins.
 * 
 * @author adamsko
 * 
 */
public interface RoundsMaster extends MapsLoaderCoordinator, Nullable {

	void addPhase(final RoundPhase newPhase);

	void addPhase(PhaseHeroes phaseHeroes);

	void addPhase(PhaseEnemies phaseEnemies);

	PhaseHeroes getPhaseHeroes();

	PhaseEnemies getPhaseEnemies();

	/**
	 * All phases ended, begin new round.
	 */
	void nextRound();

	RoundPhase getCurrentPhase();

	/**
	 * Invoked by one of the phases: information, that phase is over, has ended
	 * its work.
	 * 
	 * @param phaseEnded
	 *            phase which has ended right now
	 * @throws Exception
	 */
	void phaseIsOver(final RoundPhase phaseEnded);

	/**
	 * Start the next phase.
	 */
	void startNextPhase() throws Exception;

	/**
	 * Reload: reload all phases, reload all World objects.
	 */
	void reload();

	public GameResultMaster getGameResultMaster();

	public GameMemento createMemento();

	void setMemento(final GameMemento gameMemento);

}
