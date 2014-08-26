package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.tile.TilesMasterClient;

public interface RoundPhase extends TilesMasterClient, GuiMasterClient {

	/**
	 * Function to invoke, when phase is over.
	 * Phase should invoke 'phaseIsOver' for {@link RoundsMaster}
	 * 
	 * @param masterToInform
	 */
	void phaseIsOver(RoundPhase phaseOver) throws Exception;
	void startPhase();
	void reloadPhase();
	void setRoundsMaster(RoundsMaster roundsMaster);
	String getName();
		
}
