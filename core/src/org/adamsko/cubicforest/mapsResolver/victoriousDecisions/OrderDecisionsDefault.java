package org.adamsko.cubicforest.mapsResolver.victoriousDecisions;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.players.resolver.OrderDecisionDefault;

public class OrderDecisionsDefault implements OrderDecisions {

	/**
	 * Decisions (in increasing order), which issued one after another to the
	 * heroes, results in victory
	 */
	List<OrderDecisionDefault> solvingDecisions;

	public OrderDecisionsDefault() {
		solvingDecisions = new ArrayList<OrderDecisionDefault>();
	}

	@Override
	public void pushDecision(final OrderDecisionDefault orderDecision) {
		solvingDecisions.add(orderDecision);

	}

	@Override
	public List<OrderDecisionDefault> getDecisions() {
		return solvingDecisions;
	}

}
