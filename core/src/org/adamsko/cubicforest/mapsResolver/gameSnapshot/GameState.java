package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.OrderDecision;
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
			final int currentHeroIndex,
			final List<OrderDecision> possibleChoices) {

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

}
