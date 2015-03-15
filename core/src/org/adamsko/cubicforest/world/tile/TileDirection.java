package org.adamsko.cubicforest.world.tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TileDirection {
	N, // increasing Y coord
	E, // increasing X coord
	S, // decreasing Y coord
	W, // decreasing X coord
	NULL;

	private static List<TileDirection> directions;

	private static void regenerateList() {
		directions = new ArrayList<TileDirection>();
		directions.add(E);
		directions.add(W);
		directions.add(N);
		directions.add(S);
		Collections.shuffle(directions);
	}

	public static TileDirection randomTileDirection() {
		if (directions != null && directions.size() > 0) {
			return directions.remove(0);
		} else {
			// handle as you wish: return null or regenerate the list
			regenerateList();
			return randomTileDirection();
		}
	}

}
