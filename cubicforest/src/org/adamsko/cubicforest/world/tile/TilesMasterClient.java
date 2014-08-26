package org.adamsko.cubicforest.world.tile;

import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;

public interface TilesMasterClient {
	void onTileEvent(Tile tile , TileEvent event);
}
