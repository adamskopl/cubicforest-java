package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.Gdx;

public class RoundsMasterDefault implements RoundsMaster {

	private MapsLoader mapsLoader;
	private final List<RoundPhase> phases;
	int phasePointer = -1;
	private GameResult gameResult;

	// For NullRoundsMaster
	RoundsMasterDefault() {
		phases = null;
	}

	public RoundsMasterDefault(final MapsLoader mapsLoader) {
		this.mapsLoader = mapsLoader;
		phases = new ArrayList<RoundPhase>();
		gameResult = GameResult.GAME_PLAY;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public GameResult getGameResult() {
		return gameResult;
	}

	@Override
	public void setGameResultSingle(final GameResult gameResult) {
		if (gameResult != GameResult.GAME_PLAY
				&& this.gameResult == GameResult.GAME_PLAY) {
			this.gameResult = gameResult;
		}
	}

	@Override
	public void resetGameResult() {
		this.gameResult = GameResult.GAME_PLAY;
	}

	@Override
	public void nextRound() throws Exception {
		phasePointer = -1;
		if (allPhasesSkipped()) {
			// all phases were skipped: stop program
			Gdx.app.error("RoundsMaster::nextRound()",
					"all phases skipped. STOP.");
			return;
		}
		startNextPhase();
	}

	private RoundPhase currentPhase() {
		if (phases.size() == 0) {
			return null;
		}
		return phases.get(phasePointer);
	}

	@Override
	public void startNextPhase() throws Exception {
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

	@Override
	public void phaseIsOver(final RoundPhase phaseEnded) throws Exception {
		if (phases.get(phasePointer) != phaseEnded) {
			throw new Exception("phaseIsOver: phasePointer error");
		}
		startNextPhase();
	}

	@Override
	public void onTilePicked(final Tile tile) {
		final RoundPhase currentPhase = currentPhase();

		if (currentPhase != null) {
			currentPhase.onTilePicked(tile);
		}

	}

	@Override
	public void addPhase(final RoundPhase newPhase) {
		phases.add(newPhase);
	}

	@Override
	public void setMapActive(final int activeMapIndex) {
		mapsLoader.setMapActive(activeMapIndex);
	}

	@Override
	public void reloadWorldMapsLoader() {
		mapsLoader.reloadWorld();
	}

	@Override
	public void reload() {

		reloadWorldMapsLoader();

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
	public void onGuiEvent(final GuiElementsContainer eventGui) {
		currentPhase().onGuiEvent(eventGui);
	}

	/**
	 * Check if all phases were skipped
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