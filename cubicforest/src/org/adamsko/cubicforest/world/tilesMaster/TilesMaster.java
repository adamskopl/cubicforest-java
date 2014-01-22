package org.adamsko.cubicforest.world.tilesMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.pickmaster.PickMaster;
import org.adamsko.cubicforest.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Tiles managing class. Map's model: <br>
 * . X---> <br>
 * Y 0 1 2 <br>
 * | 3 4 5 <br>
 * V 6 7 8 <br>
 * 
 * @author adamsko
 */
public class TilesMaster implements PickMasterClient {

	/**
	 * Event types connected with a {@link Tile}.
	 * 
	 * @author adamsko
	 * 
	 */
	public enum TileEvent_e {
		/**
		 * {@link Tile} received input from {@link PickMaster}.
		 */
		TILE_PICKED		
	}
	
	// number of tiles (mapSize = 16 -> 4x4 tiles)
	private int mapSize;
	private TilesContainer tilesContainer;
	
	public TilesMaster(int mapSize) {
		this.mapSize = mapSize;
		TilesMasterHelper.setMapSize(mapSize);
		initTiles();
	}
	
	public void initTiles() {
		tilesContainer = new TilesContainer(this);
		for(int fIndex = 0; fIndex < mapSize; fIndex++) {
			if(TilesMasterHelper.isTileonTestMap(fIndex)) {continue;}			
			Vector2 fCoords = TilesMasterHelper.calcCoords(fIndex);
			fCoords.add(new Vector2(7, -3)); // temporary solution for centering view
			tilesContainer.addTile(fCoords);
		}
	}
	
	public TilesContainer getTilesContainer() {
		return tilesContainer;
	}
	
	/**
	 * Associate given {@link WorldObject} object with a {@link Tile} object.
	 * 
	 * @param insertObject
	 *            {@link WorldObject} object to be associated with a
	 *            {@link Tile}.
	 */
	public void insertWorldObject(WorldObject insertObject) {
		Tile parentTile = tilesContainer.getTileOnPos(insertObject.getTilesPos());
		if(parentTile != null) {
			parentTile.insertWorldObject(insertObject);
			tilesContainer.testHighlightTile(parentTile, 1);
		}
	}
	
	/**
	 * Temporary test: get tiles for a test path.
	 */
	public List<Tile> getPathTestTiles() {
		return tilesContainer.getPathTestTiles();
	}
	
	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {	
		Tile clickedTile = tilesContainer.getTileOnPos(inputTilesPos);
		if(clickedTile != null) {
			tilesContainer.testHighlightTile(clickedTile, 2);
			clickedTile.handleTileEvent(TileEvent_e.TILE_PICKED);
		}
	}
}