package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.roundsMaster.GameResult;

public class GameState {

	// index of the current hero
	private final int currentHeroIndex;
	private final WMContainerMemento wMCMemento;

	private final GameResult gameResult;

	// if the game is lost/won no other data is necessary
	public GameState(final GameResult gameResult) {
		this.gameResult = gameResult;

		this.currentHeroIndex = 0;
		this.wMCMemento = null;
	}

	public GameState(final WMContainerMemento wmContainerMemento,
			final int currentHeroIndex) {

		this.currentHeroIndex = currentHeroIndex;
		this.wMCMemento = wmContainerMemento;
		this.gameResult = GameResult.GAME_PLAY;
	}

	public int getCurrentHeroIndex() {
		return currentHeroIndex;
	}

	public WMContainerMemento getWMContainerMemento() {
		return wMCMemento;
	}

	public GameResult getGameResult() {
		return this.gameResult;
	}

	public boolean isEqual(final GameState checkedState) {

		if (currentHeroIndex != checkedState.getCurrentHeroIndex()) {
			return false;
		}

		// compare WorldObjectsMaster mementos for this state with memento
		// checkedState
		return (getWMContainerMemento().isEqual(checkedState
				.getWMContainerMemento()));

	}

}
