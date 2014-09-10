package org.adamsko.cubicforest.world.tile.tilesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;

public class AwayTilesSearcherDefault implements AwayTilesSearcher {

	private final TilesContainer tilesContainer;
	private int distance;
	private float posX, posY;
	private TilesSearchParameter tilesSearchParameter;
	private final List<Tile> returnList;

	public AwayTilesSearcherDefault(final TilesMaster tilesMaster) {
		this.tilesContainer = tilesMaster.getTilesContainer();
		returnList = new ArrayList<Tile>();
	}

	@Override
	public List<Tile> getTilesAway(final Tile tile, final int distance,
			final TilesSearchParameter tilesSearchParameter) {
		this.distance = distance;
		this.tilesSearchParameter = tilesSearchParameter;
		returnList.clear();
		posX = (int) tile.getTilesPos().x;
		posY = (int) tile.getTilesPos().y;

		// tiles are arranged in a diamond shape
		// right-down diagonal
		addDiagonal(posX - distance, posY, 1, 1);
		// right-up diagonal
		addDiagonal(posX, posY + distance, 1, -1);
		// left-up diagonal
		addDiagonal(posX + distance, posY, -1, -1);
		// left-down diagonal
		addDiagonal(posX, posY - distance, -1, 1);

		return returnList;
	}

	/**
	 * Search diagonal defined by xDir, yDir for tiles
	 * 
	 * @param xDir
	 *            horizontal direction of diagonal
	 * @param yDir
	 *            vertical direction of diagonal
	 */
	private void addDiagonal(final float tileX, final float tileY,
			final int xDir, final int yDir) {
		// diagonal counter
		float dc;
		// value modification
		float mod;
		for (dc = 0; dc < distance; dc++) {
			mod = distance - dc;
			final Tile nextTile = tilesContainer.getTileOnPos(tileX + xDir
					* mod, tileY + yDir * mod);
			if (!nextTile.isNull()
					&& nextTile
							.isTileValidSearchParameter(tilesSearchParameter)) {
				returnList.add(nextTile);
			}
		}
	}
}
