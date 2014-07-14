package org.adamsko.cubicforest.world.tilesMaster;

import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

public interface TilesMasterClient {
	void onTileEvent(Tile tile , TileEvent event);
}
