package org.adamsko.cubicforest.world.tilesMaster;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

/**
 * Collection of TilesMaster helpful functions.
 * 
 * @author adamsko
 * 
 */
public class TilesMasterHelper {

	// number of tiles (mapSize = 16 -> 4x4 tiles)
	static int mapSize = 1;
	// sqrt of the mapSize
	static int sideSize = 1;
	
	private static List<Integer> testEmptyTiles;
	
	/**
	 * DESC
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
	 * Get coordinates for tileIndex(th) tile.
	 * Map's model: <br>
	 * . X---> <br>
	 * Y 0 1 2 <br>
	 * | 3 4 5 <br>
	 * V 6 7 8 <br>
	 * @param tileIndex index of the map's tile
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
	
}