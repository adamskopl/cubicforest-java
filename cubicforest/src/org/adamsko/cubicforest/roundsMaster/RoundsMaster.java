package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.World;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.ACubemap;

/**
 * A round consists of phases.
 * 
 * @author adamsko
 * 
 */
public class RoundsMaster implements TilesMasterClient, GuiMasterClient {

	/**
	 * for the rounds/world restart
	 */
	private World world;
	private List<RoundPhase> phases;
	int phasePointer = -1;
	/**
	 * Block reloading and unblock it later (one click = one reload)
	 */
	private boolean reloadAllowed = true;

	public RoundsMaster(World world) {
		this.world = world;
		phases = new ArrayList<RoundPhase>();
	}

	public void nextRound() throws Exception {
		phasePointer = -1;
		nextPhase();
	}

	private RoundPhase actualPhase() {
		if (phases.size() == 0)
			return null;
		return phases.get(phasePointer);
	}

	public void nextPhase() throws Exception {
		if (phases.size() == 0)
			return;

		phasePointer++;
		// check if previous phase was the last one
		if (phasePointer == phases.size()) {
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
		if (phases.get(phasePointer) != phaseEnded) {
			throw new Exception("phaseIsOver: phasePointer error");
		}
		nextPhase();

	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		RoundPhase actualPhase = actualPhase();

		if (actualPhase != null)
			actualPhase.onTileEvent(tile, event);

	}

	/**
	 * @param newPhase
	 */
	public void addPhase(RoundPhase newPhase) {
		phases.add(newPhase);
	}

	/**
	 * Reload {@link RoundsMaster}: reload all phases, reload World.
	 */
	public void reload() {
		if (reloadAllowed) {
			reloadAllowed = false;

			world.reloadWorld();

			// reload phases after reloading World (add new phaseObjects)
			reloadPhases();

			try {
				nextRound();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isReloadAllowed() {
		return reloadAllowed;
	}

	private void reloadPhases() {
		for (RoundPhase phase : phases) {
			phase.reloadPhase();
		}
	}

	public void reloadUnlock() {
		reloadAllowed = true;
	}

	/**
	 * Modify actual order. Used by {@link InteractionObjectsMaster} objects to
	 * modify actually issued orders.
	 */
	public void orderOperation(OrderOperation_e orderOperation) {
		RoundPhase actualPhase = actualPhase();
		if(actualPhase != null) {
			actualPhase.orderOperation(orderOperation);
		}
	}

	@Override
	public void onGuiEvent(GuiContainer eventGui) {
		actualPhase().onGuiEvent(eventGui);
	}

}
