package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

public interface RoundPhase extends TilesMasterClient {

	boolean hasPhaseEnded();
	void startPhase();
		
}
