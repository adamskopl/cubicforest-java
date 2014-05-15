package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

public interface RoundPhase extends TilesMasterClient, GuiMasterClient {

	/**
	 * Function to invoke, when phase is over.
	 * Phase should invoke 'phaseIsOver' for {@link RoundsMaster}
	 * 
	 * @param masterToInform
	 */
	void phaseIsOver() throws Exception;
	void startPhase();
	void reloadPhase();
	void setRoundsMaster(RoundsMaster roundsMaster);
	/**
	 * Modify actual order. Used by {@link InteractionObjectsMaster} objects to
	 * modify actually issued orders.
	 * @param orderOperation
	 */
	void orderOperation(OrderOperation_e orderOperation);
	String getName();
		
}
