package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

public class GameSnapshotMementoDefault implements GameMemento {

	private GameState gameState;

	public static int tempIdCounter = 0;
	public int tempId;

	public GameSnapshotMementoDefault() {
		tempId = tempIdCounter;
		tempIdCounter++;
	}

	@Override
	public int getTempId() {
		return tempId;
	}

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
