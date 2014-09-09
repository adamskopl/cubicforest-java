package org.adamsko.cubicforest.world.tilePathsMaster.searcher;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathDefault;

import com.badlogic.gdx.Gdx;

/**
 * Implementation of search method: look for the shortest valid path. Such path
 * leads from starting tile to the ending tile and is uninterrupted.
 * 
 * @author adamsko
 * 
 */
public class TilePathSearcherValidPath implements TilePathSearcher {

	private TilesMaster tilesMaster = null;
	private TilePathSearcherVPHelper helper;

	@Override
	public void setTilesMaster(final TilesMaster tilesMaster) {
		this.tilesMaster = tilesMaster;
		helper = new TilePathSearcherVPHelper(tilesMaster);
	}

	@Override
	public TilePath search(final Tile from, final Tile to) {
		if (from == to) {
			// return path with 'from' tile
			return new TilePathDefault(from);
		}

		TilePath path = null;

		helper.searchCostTiles(from, to);

		try {
			path = helper.costTilesToPath();
		} catch (final Exception e) {
			Gdx.app.error("TilePath::search()", e.toString());
		}

		return path;
	}

	@Override
	public TilePath search(final WorldObject objectOnPath, final Tile destTile) {
		final Tile srcTile = tilesMaster.getTileWithObject(objectOnPath);

		if (srcTile.isNull()) {
			Gdx.app.error("search", "NULL");
		}

		return search(srcTile, destTile);
	}

	@Override
	public TilePath search(final WorldObject objectFrom,
			final WorldObject objectTo) {
		final Tile srcTile = tilesMaster.getTileWithObject(objectFrom);
		final Tile destTile = tilesMaster.getTileWithObject(objectTo);

		final TilePath searchedPath = search(srcTile, destTile);
		return searchedPath;
	}

}
