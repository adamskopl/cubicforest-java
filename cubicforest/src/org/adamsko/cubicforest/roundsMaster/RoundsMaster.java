package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

/**
 * A round consists of phases. 
 * 
 * @author adamsko
 *
 */
public class RoundsMaster implements TilesMasterClient, GuiMasterClient {

	private List<RoundPhase> phases;
	int phasePointer = -1;
	
	public RoundsMaster() {
		phases = new ArrayList<RoundPhase>();
	}
	
	public void nextRound() throws Exception {
		phasePointer = -1;	
		nextPhase();
	}
	
	private RoundPhase actualPhase() {
		return phases.get(phasePointer);
	}
	
	public void nextPhase() throws Exception {
		phasePointer++;
		// check if previous phase was the last one 
		if(phasePointer == phases.size()) {
			nextRound();
		} else {		
			RoundPhase nextPhase = phases.get(phasePointer);
			nextPhase.startPhase();
		}
	}

	/**
	 * Invoked by one of the phases: information, that phase is over, has ended
	 * its work.
	 * 
	 * @param phaseEnded
	 *            phase which has ended right now
	 * @throws Exception 
	 */
	public void phaseIsOver(RoundPhase phaseEnded) throws Exception {
		if(phases.get(phasePointer) != phaseEnded) {
			throw new Exception("phaseIsOver: phasePointer error");
		}
		nextPhase();

	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		actualPhase().onTileEvent(tile, event);

	}
	
	/**
	 * @param newPhase
	 */
	public void addPhase(RoundPhase newPhase) {
		phases.add(newPhase);
	}

	@Override
	public void onGuiEvent(GuiContainer eventGui) {
		actualPhase().onGuiEvent(eventGui);		
	}

}
