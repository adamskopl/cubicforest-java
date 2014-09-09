package org.adamsko.cubicforest.world.tilePathsMaster.searcher;

import org.adamsko.cubicforest.world.tile.TilesMaster;

public class TilePathSearchersMasterDefault implements TilePathSearchersMaster {

	TilePathSearcher tilePathSearcherValidPath;
	TilePathSearcher tilePathSearcherNearestTile;

	public TilePathSearchersMasterDefault(final TilesMaster tilesMaster) {
		tilePathSearcherValidPath = new TilePathSearcherValidPath();
		tilePathSearcherValidPath.setTilesMaster(tilesMaster);
		tilePathSearcherNearestTile = new TilePathSearcherNearestTile();
	}

	@Override
	public TilePathSearcher getTilePathSearcherValidPath() {
		return tilePathSearcherValidPath;
	}

	@Override
	public TilePathSearcher getTilePathSearcherNearestTile() {
		return tilePathSearcherNearestTile;
	}

}
