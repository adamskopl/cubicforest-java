package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.World;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

import com.badlogic.gdx.Gdx;

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

	private GameResult gameResult;

	public GameResult getGameResult() {
		return gameResult;
	}

	/**
	 * Changes game result. It can be changed from 'PLAY' only once until
	 * 'resetGameResult()' is not invoked..
	 * 
	 * @param gameResult
	 */
	public void setGameResultSingle(GameResult gameResult) {
		if (gameResult != GameResult.GAME_PLAY
				&& this.gameResult == GameResult.GAME_PLAY) {
			Gdx.app.debug("setres", gameResult.toString());
			this.gameResult = gameResult;
		}
	}

	/**
	 * Invoked when game result is read. From now game result can be changed.
	 */
	public void resetGameResult() {
		this.gameResult = GameResult.GAME_PLAY;
	}

	public RoundsMaster(World world) {
		this.world = world;
		phases = new ArrayList<RoundPhase>();
		gameResult = GameResult.GAME_PLAY;
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
	public void onTileEvent(Tile tile, TileEvent event) {
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

	@Override
	public void onGuiEvent(GuiContainer eventGui) {
		actualPhase().onGuiEvent(eventGui);
	}

}
