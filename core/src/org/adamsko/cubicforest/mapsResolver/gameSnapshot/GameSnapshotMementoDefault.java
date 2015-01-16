package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

public class GameSnapshotMementoDefault implements GameMemento {

	private GameState gameState;

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public GameState getState() {
		return gameState;
	}

	@Override
	public void setState(final GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public boolean isEqual(final GameMemento checkedMemento) {
		return checkedMemento.getState().isEqual(getState());
	}

}
