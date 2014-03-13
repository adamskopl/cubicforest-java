package org.adamsko.cubicforest.roundsMaster;

import java.util.List;

import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

public class RoundsMaster implements TilesMasterClient {

	private List<RoundPhase> phases;
	
	public RoundsMaster() {
		
	}

	/**
	 * Invoked by one of the phases: information, that phase is over, has ended
	 * its work.
	 * 
	 * @param phaseEnded
	 *            phase which has ended right now
	 */
	public void phaseIsOver(RoundPhase phaseEnded) {

	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @param newPhase
	 */
	public void addPhase(RoundPhase newPhase) {
		phases.add(newPhase);
	}

}
