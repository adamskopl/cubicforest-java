package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

public class GameSnapshotMementoDefault implements GametMemento {

	private GameState state;

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public GameState getState() {
		return state;
	}

	@Override
	public void setState(final GameState s) {
		this.state = state;
	}

}
