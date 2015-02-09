package org.adamsko.cubicforest.mapsResolver.gameSnapshot;

import org.adamsko.cubicforest.Nullable;

public interface GameMemento extends Nullable {

	GameState getState();

	void setState(GameState s);

	boolean isEqual(GameMemento m);

	int getTempId();

}
