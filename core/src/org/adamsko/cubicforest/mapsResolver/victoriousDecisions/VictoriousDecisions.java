package org.adamsko.cubicforest.mapsResolver.victoriousDecisions;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.OrderDecisionDefault;

/**
 * Interface for class representing the sequence of decisions giving victory
 * condition.
 * 
 */
public interface VictoriousDecisions {

	/**
	 * Add next decision belonging to the game solution.
	 */
	public void pushDecision(OrderDecisionDefault orderDecision);

	/**
	 * Get list of decisions belonging to the game solution.
	 */
	List<OrderDecisionDefault> getDecisions();

}
