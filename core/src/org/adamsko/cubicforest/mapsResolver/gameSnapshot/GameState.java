package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMaster;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResultMasterDefault;

public class GameState {

	// index of the current hero
	private final int currentHeroIndex;
	private final WMContainerMemento wMCMemento;

	/**
	 * GameState has its own {@link GameResultMaster}.
	 */
	private final GameResultMaster gameResultMaster;

	// if the game is lost/won no other data is necessary
	public GameState(final GameResultMaster gameResultMaster) {
		this.gameResultMaster = new GameResultMasterDefault(gameResultMaster);

		this.currentHeroIndex = 0;
		this.wMCMemento = null;
	}

	public GameState(final WMContainerMemento wmContainerMemento,
			final int currentHeroIndex) {

		this.currentHeroIndex = currentHeroIndex;
		this.wMCMemento = wmContainerMemento;
		this.gameResultMaster = new GameResultMasterDefault();
	}

	public int getCurrentHeroIndex() {
		return currentHeroIndex;
	}

	public WMContainerMemento getWMContainerMemento() {
		return wMCMemento;
	}

	public GameResultMaster getGameResultMaster() {
		return this.gameResultMaster;
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
