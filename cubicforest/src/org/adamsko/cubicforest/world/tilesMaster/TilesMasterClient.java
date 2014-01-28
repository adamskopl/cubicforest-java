package org.adamsko.cubicforest.world.tilesMaster;

import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

public interface TilesMasterClient {
	void onTileEvent(Tile tile , TileEvent_e event);
}
