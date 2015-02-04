package org.adamsko.cubicforest.players.decisionOrdersReplay;

import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionsAggregate;
import org.adamsko.cubicforest.players.Player;

public interface PlayerDecisionOrdersReplay extends Player {

	/**
	 * Set {@link OrderDecisionsAggregate} from which replay will be performed.
	 * Should be used before player starts replay.
	 */
	void setDecisionOrdersAggregate(OrderDecisionsAggregate replayedAggregate);

}
