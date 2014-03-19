package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

public interface RoundPhase extends TilesMasterClient {

	/**
	 * Function to invoke, when phase is over.
	 * Phase should invoke 'phaseIsOver' for {@link RoundsMaster}
	 * 
	 * @param masterToInform
	 */
	void phaseIsOver() throws Exception;
	void startPhase();
	void setRoundsMaster(RoundsMaster roundsMaster);
		
}
