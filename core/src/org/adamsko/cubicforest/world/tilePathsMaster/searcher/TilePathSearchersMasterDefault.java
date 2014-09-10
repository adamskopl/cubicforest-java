package org.adamsko.cubicforest.world.tilePathsMaster.searcher;

import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameterFactory;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.nearestTile.TilePathSearcherNearestTile;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.validPath.TilePathSearcherValidPath;

public class TilePathSearchersMasterDefault implements TilePathSearchersMaster {

	TilesSearchParameterFactory tilesSearchParameterFactory;

	TilePathSearcher tilePathSearcherValidPath;
	TilePathSearcher tilePathSearcherNearestTile;

	public TilePathSearchersMasterDefault(final TilesMaster tilesMaster) {
		this.tilePathSearcherValidPath = new TilePathSearcherValidPath(
				tilesMaster);
		this.tilePathSearcherNearestTile = new TilePathSearcherNearestTile(
				tilesMaster, tilePathSearcherValidPath);
		this.tilesSearchParameterFactory = tilesMaster
				.getTilesSearchParameterFactory();
	}

	@Override
	public TilesSearchParameterFactory getTilesSearchParameterFactory() {
		return tilesSearchParameterFactory;
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
