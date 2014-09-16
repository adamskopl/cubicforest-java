package org.adamsko.cubicforest.mapsResolver.gameSnapshot;


public class NullGameSnapshotMemento extends GameSnapshotMementoDefault {
	private static NullGameSnapshotMemento instance = null;

	private NullGameSnapshotMemento() {
		super();
	}

	public static NullGameSnapshotMemento instance() {
		if (instance == null) {
			instance = new NullGameSnapshotMemento();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
