package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

import org.adamsko.cubicforest.Nullable;

public interface GametMemento extends Nullable {

	GameState getState();

	void setState(GameState s);

}
