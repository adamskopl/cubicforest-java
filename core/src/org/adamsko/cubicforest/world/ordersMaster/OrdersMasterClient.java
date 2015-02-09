package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.players.PlayerActionsHandler;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;

public interface OrdersMasterClient {

	/**
	 * TODO: should be not in concrete {@link RoundPhase} phases but in proper
	 * {@link PlayerActionsHandler}
	 */
	public void onOrderFinished();

}
