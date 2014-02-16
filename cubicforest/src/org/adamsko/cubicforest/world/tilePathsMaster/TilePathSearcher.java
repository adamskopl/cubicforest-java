package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;

/**
 * Generates {@link TilePath} object from (...)
 * 
 * @author adamsko
 * 
 */
public class TilePathSearcher {

	private static TilesMaster tilesMaster = null;
	private static TilePathSearcherHelper helper;

	public static void setTilesMaster(TilesMaster tilesMaster) {
		TilePathSearcher.tilesMaster = tilesMaster;
		helper = new TilePathSearcherHelper(tilesMaster);
	}

	public static TilePath search(WorldObject objectOnPath, Tile destTile) {
		Tile srcTile = tilesMaster.getTilesContainer().getTileWithObject(
				objectOnPath);
		return search(srcTile, destTile);
	}

	/**
	 * @param from
	 * @param to
	 * @return Founded {@link TilePath}, starting with {@link Tile} next to
	 *         given 'from' {@link Tile} object
	 */
	public static TilePath search(Tile from, Tile to) {
		if (from == to) {
			return new TilePath();
		}

		TilePath path = null;

		helper.searchCostTiles(from, to);
		try {
			path = helper.costTilesToPath();
		} catch (Exception e) {
			Gdx.app.error("TilePath::search()", e.toString());
		}
		return path;
	}

}
