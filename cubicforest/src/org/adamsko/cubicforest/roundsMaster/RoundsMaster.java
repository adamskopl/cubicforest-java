package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;
import org.adamsko.cubicforest.world.tile.TilesMasterClient;

import com.badlogic.gdx.Gdx;

/**
 * A round consists of phases.
 * 
 * @author adamsko
 * 
 */
public class RoundsMaster implements TilesMasterClient, GuiMasterClient,
		Nullable {

	private MapsLoader mapsLoader;
	private final List<RoundPhase> phases;
	int phasePointer = -1;
	private GameResult gameResult;

	// For NullRoundsMaster
	RoundsMaster() {
		phases = null;
	}

	public RoundsMaster(final MapsLoader mapsLoader) {
		this.mapsLoader = mapsLoader;
		phases = new ArrayList<RoundPhase>();
		gameResult = GameResult.GAME_PLAY;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	/**
	 * Changes game result. It can be changed from 'PLAY' only once until
	 * 'resetGameResult()' is not invoked..
	 * 
	 * @param gameResult
	 */
	public void setGameResultSingle(final GameResult gameResult) {
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

	public void nextRound() throws Exception {
		phasePointer = -1;
		if (allPhasesSkipped()) {
			// all phases were skipped: stop program
			Gdx.app.error("RoundsMaster::nextRound()",
					"all phases skipped. STOP.");
			return;
		}
		nextPhase();
	}

	private RoundPhase currentPhase() {
		if (phases.size() == 0) {
			return null;
		}
		return phases.get(phasePointer);
	}

	public void nextPhase() throws Exception {
		if (phases.size() == 0) {
			return;
		}

		phasePointer++;
		// check if previous phase was the last one
		if (phasePointer == phases.size()) {
			nextRound();
		} else {
			final RoundPhase nextPhase = phases.get(phasePointer);
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
	public void phaseIsOver(final RoundPhase phaseEnded) throws Exception {
		if (phases.get(phasePointer) != phaseEnded) {
			throw new Exception("phaseIsOver: phasePointer error");
		}
		nextPhase();
	}

	@Override
	public void onTileEvent(final Tile tile, final TileEvent event) {
		final RoundPhase currentPhase = currentPhase();

		if (currentPhase != null) {
			currentPhase.onTileEvent(tile, event);
		}

	}

	/**
	 * @param newPhase
	 */
	public void addPhase(final RoundPhase newPhase) {
		phases.add(newPhase);
	}

	public void setMapActive(final int activeMapIndex) {
		mapsLoader.setMapActive(activeMapIndex);
	}

	/**
	 * Reload {@link RoundsMaster}: reload all phases, reload World.
	 */
	public void reload() {

		mapsLoader.reloadWorld();

		// reload phases after reloading World (add new phaseObjects)
		reloadPhases();

		try {
			nextRound();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void reloadPhases() {
		for (final RoundPhase phase : phases) {
			phase.reloadPhase();
		}
	}

	@Override
	public void onGuiEvent(final GuiContainer eventGui) {
		currentPhase().onGuiEvent(eventGui);
	}

	/**
	 * Check if all phases were skipped
	 * 
	 * @return
	 */
	private boolean allPhasesSkipped() {
		for (final RoundPhase roundPhase : phases) {
			if (!roundPhase.phaseSkippedLastTime()) {
				// at least one phase was not skipped
				return false;
			}
		}
		return true;
	}
}
