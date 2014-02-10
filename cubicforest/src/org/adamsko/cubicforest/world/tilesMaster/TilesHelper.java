package org.adamsko.cubicforest.world.tilesMaster;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

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
	
	public static String toString(TilesConnection_e conn) {
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

	private static List<Integer> testEmptyTiles;

	/**
	 * DESC
	 * 
	 * @param size
	 */
	public static void setMapSize(int size) {
		mapSize = size;
		sideSize = (int) Math.sqrt(size);

		testEmptyTiles = new ArrayList<Integer>();
		testEmptyTiles.add(8);
		testEmptyTiles.add(9);
		testEmptyTiles.add(10);
		testEmptyTiles.add(24);
		testEmptyTiles.add(28);
		testEmptyTiles.add(29);
		testEmptyTiles.add(36);
		testEmptyTiles.add(37);
		testEmptyTiles.add(48);
		testEmptyTiles.add(49);
		testEmptyTiles.add(50);

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
	public static Vector2 calcCoords(int tileIndex) {
		Vector2 coords = new Vector2();
		coords.y = tileIndex / sideSize;
		coords.x = tileIndex % sideSize;

		return coords;
	}

	// temporary function for test map
	public static boolean isTileonTestMap(int tileIndex) {
		return testEmptyTiles.contains(tileIndex);
	}

	/**
	 * @param tileA
	 * @param tileB
	 * @return
	 */
	public static Boolean areTilesAdjecant(Tile tileA, Tile tileB) {
		float xDiff = Math.abs(tileA.getTilesPosX() - tileB.getTilesPosX());
		float yDiff = Math.abs(tileA.getTilesPosY() - tileB.getTilesPosY());

		if (xDiff == 1 && yDiff == 0)
			return true;
		if (yDiff == 1 && xDiff == 0)
			return true;

		return false;
	}

	public static TilesConnection_e getConnectionType(Tile tileA, Tile tileB) {
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
	public static Vector2 getPosBetween(Tile tileA, Tile tileB)
			throws Exception {
		TilesConnection_e connType = getConnectionType(tileA, tileB);
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
	 * @param tiles list of tiles to convert
	 * @return String containing tiles converted to String
	 */
	public static String toString(List<Tile> tiles) {
		String ret = new String();
		for(Tile t : tiles) {
			ret+=t.toString() + " ";
		}
		return ret;
	}
}