package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.mapsResolver.MapsResolver;

/**
 * Round Decisions Iterator Solving Coordinator. Interface for classes using
 * {@link RoundDecisionsIterator}
 * 
 */
public interface RDIterSolvingCoordinator {

	/**
	 * Set {@link RoundDecisionsIterator} object.
	 */
	void setRoundDecisionsIterator(
			final RoundDecisionsIterator roundDecisionsIterator,
			final MapsResolver mapsResolver);

	/**
	 * Iterate through Iterator's one element.
	 */
	void solveIter();

}
