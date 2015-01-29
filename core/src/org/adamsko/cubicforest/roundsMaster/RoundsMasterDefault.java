package org.adamsko.cubicforest.roundsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameSnapshotMementoDefault;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameState;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.players.resolver.MapsResolver;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMaster;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMasterDefault;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;

import com.badlogic.gdx.Gdx;

/**
 * @author adamsko
 * 
 */
/**
 * @author adamsko
 * 
 */
public class RoundsMasterDefault implements RoundsMaster {

	private final MapsLoader mapsLoader;
	private PhaseHeroes phaseHeroes;
	private PhaseEnemies phaseEnemies;
	private final List<RoundPhase> phases;
	private int phasePointer = 0;
	private GameResultMaster gameResultMaster;
	private WorldObjectsMastersContainer worldObjectsMastersContainer;

	// For NullRoundsMaster
	RoundsMasterDefault() {
		mapsLoader = null;
		phases = null;
	}

	public RoundsMasterDefault(final MapsLoader mapsLoader,
			final MapsResolver mapsResolver,
			final WorldObjectsMastersContainer worldObjectsMastersContainer) {
		this.mapsLoader = mapsLoader;
		phases = new ArrayList<RoundPhase>();
		this.gameResultMaster = new GameResultMasterDefault();
		this.worldObjectsMastersContainer = worldObjectsMastersContainer;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public GameResultMaster getGameResultMaster() {
		return gameResultMaster;
	}

	@Override
	public void startNextRound() {
		phasePointer = -1;
		if (allPhasesSkipped()) {
			// all phases were skipped: stop program
			Gdx.app.error("RoundsMaster::nextRound()",
					"all phases skipped. STOP.");
			return;
		}
		startNextPhase();
	}

	@Override
	public void startNextPhase() {
		if (phases.size() == 0) {
			return;
		}

		phasePointer++;
		// check if previous phase was the last one
		if (phasePointer == phases.size()) {
			startNextRound();
		} else {
			final RoundPhase nextPhase = phases.get(phasePointer);
			nextPhase.startPhase();
		}
	}

	@Override
	public void phaseIsOver(final RoundPhase phaseEnded) {
		if (phases.get(phasePointer) != phaseEnded) {
			Gdx.app.error("RoundsMasterDefault",
					"phaseIsOver: phasePointer error");
		}
		startNextPhase();
	}

	@Override
	public void addPhase(final RoundPhase newPhase) {
		phases.add(newPhase);
	}

	@Override
	public void addPhase(final PhaseHeroes phaseHeroes) {
		this.phaseHeroes = phaseHeroes;
		phases.add(phaseHeroes);
	}

	@Override
	public void addPhase(final PhaseEnemies phaseEnemies) {
		this.phaseEnemies = phaseEnemies;
		phases.add(phaseEnemies);
	}

	@Override
	public PhaseHeroes getPhaseHeroes() {
		return phaseHeroes;
	}

	@Override
	public PhaseEnemies getPhaseEnemies() {
		return phaseEnemies;
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
			startNextRound();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setMemento(final GameMemento gameMemento) {
		// this.worldObjectsMastersContainer = new
		// WorldObjectsMastersContainerDefault();
		final GameState gameState = gameMemento.getState();

		this.worldObjectsMastersContainer.setMemento(gameState
				.getWMContainerMemento());

		phaseHeroes.reloadPhase(this.worldObjectsMastersContainer
				.getHeroesMaster());
		phaseHeroes.setCurrentObjectIndex(gameState.getCurrentHeroIndex());

		phaseEnemies.reloadPhase(this.worldObjectsMastersContainer
				.getEnemiesMaster());
	}

	@Override
	public GameMemento createMemento() {
		final WMContainerMemento wmContainerMemento = this.worldObjectsMastersContainer
				.createMemento();
		final int currentHeroIndex = phaseHeroes.getCurrentObjectIndex();

		final GameState snapshot = new GameState(wmContainerMemento,
				currentHeroIndex);

		final GameMemento memento = new GameSnapshotMementoDefault();
		memento.setState(snapshot);

		return memento;
	}

	private void reloadPhases() {
		for (final RoundPhase phase : phases) {
			phase.reloadPhase();
		}
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

	@Override
	public RoundPhase getCurrentPhase() {
		if (phases.size() == 0) {
			return null;
		}
		return phases.get(phasePointer);
	}

}
