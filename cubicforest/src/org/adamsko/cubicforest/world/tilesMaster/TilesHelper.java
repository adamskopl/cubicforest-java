package org.adamsko.cubicforest.world.tilesMaster;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

/**
 * Collection of helpful functions connected with {@link Tile}.
 * 
 * @author adamsko
 * 
 */
public class TilesHelper {

	// number of tiles (mapSize = 16 -> 4x4 tiles)
	static int mapSize = 1;
	// sqrt of the mapSize
	static int sideSize = 1;

	/**
	 * Types of connections between tiles.
	 * 
	 * @author adamsko
	 */
	public enum TilesConnection_e {
		/**
		 * the same Y coordinates
		 */
		HORIZONTAL,
		/**
		 * the same X coordinates
		 */
		VERTICAL,
		/**
		 * tiles are not connected
		 */
		NONE
	}

	public static String toString(final TilesConnection_e conn) {
		switch (conn) {
		case HORIZONTAL:
			return new String("TILES HORIZONTAL");
		case VERTICAL:
			return new String("TILES VERTICAL");
		case NONE:
			return new String("TILES NO CONNECTION");
		default:
			return new String("TILES UKNOWN CONNECTION");
		}
	}

	/**
	 * DESC
	 * 
	 * @param size
	 */
	public static void setMapSize(final int size) {
		mapSize = size;
		sideSize = (int) Math.sqrt(size);
	}

	/**
	 * Get coordinates for tileIndex(th) tile. Map's model: <br>
	 * . X---> <br>
	 * Y 0 1 2 <br>
	 * | 3 4 5 <br>
	 * V 6 7 8 <br>
	 * 
	 * @param tileIndex
	 *            index of the map's tile
	 * @return calculated coordinates on the 2d map
	 */
	public static Vector2 calcCoords(final int tileIndex) {
		final Vector2 coords = new Vector2();
		coords.y = tileIndex / sideSize;
		coords.x = tileIndex % sideSize;

		return coords;
	}

	/**
	 * @param tileA
	 * @param tileB
	 * @return
	 */
	public static Boolean areTilesAdjecant(final Tile tileA, final Tile tileB) {
		final float xDiff = Math.abs(tileA.getTilesPosX()
				- tileB.getTilesPosX());
		final float yDiff = Math.abs(tileA.getTilesPosY()
				- tileB.getTilesPosY());

		if (xDiff == 1 && yDiff == 0) {
			return true;
		}
		if (yDiff == 1 && xDiff == 0) {
			return true;
		}

		return false;
	}

	public static TilesConnection_e getConnectionType(final Tile tileA,
			final Tile tileB) {
		if (!areTilesAdjecant(tileA, tileB)) {
			return TilesConnection_e.NONE;
		}
		if (tileA.getTilesPosX() == tileB.getTilesPosX()) {
			return TilesConnection_e.VERTICAL;
		}
		return TilesConnection_e.HORIZONTAL;
	}

	/**
	 * Calculate position between adjacent tiles.
	 * 
	 * @param tileA
	 * @param tileB
	 * @return Position between tiles or null;
	 * @throws Exception
	 */
	public static Vector2 getPosBetween(final Tile tileA, final Tile tileB)
			throws Exception {
		final TilesConnection_e connType = getConnectionType(tileA, tileB);
		switch (connType) {
		case HORIZONTAL: {
			if (tileA.getTilesPosX() < tileB.getTilesPosX()) {
				return tileB.getTilesPos();
			} else {
				return tileA.getTilesPos();
			}
		}
		case VERTICAL: {
			if (tileA.getTilesPosY() < tileB.getTilesPosY()) {
				return tileB.getTilesPos();
			} else {
				return tileA.getTilesPos();
			}
		}
		case NONE: {
			throw new Exception("getPosBetween: connection: NONE");
		}
		default: {
			throw new Exception("getPosBetween: connection: unsupported");
		}
		}
	}

	/**
	 * Convert list of tiles to String
	 * 
	 * @param tiles
	 *            list of tiles to convert
	 * @return String containing tiles converted to String
	 */
	public static String toString(final List<Tile> tiles) {
		String ret = new String();
		for (final Tile t : tiles) {
			ret += t.toString() + " ";
		}
		return ret;
	}
}