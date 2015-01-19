package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.OrderDecisionDefault;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RDIterSolvingCoordinator;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;

/**
 * Interface for phase where Player is making decisions.
 * 
 * @author adamsko
 * 
 */
public interface PhaseHeroes extends PhaseOrderableObjects,
		RDIterSolvingCoordinator {

	/**
	 * Perform operations for passed {@link OrderDecisionDefault}.
	 */
	void resolveDecision(final OrderDecisionDefault orderDecision);

	/**
	 * {@link PhaseHeroesOrdersMaster#getCurrentPossbileDecisions()}
	 */
	List<OrderDecisionDefault> getCurrentPossbileDecisions();

	/**
	 * Check if game is victorious. <br>
	 * Victory conditions: there are no heroes in the heroes phase.
	 */
	boolean victoryConditionsMet();

}
