package org.adamsko.cubicforest.world.tilesMaster;

import org.adamsko.cubicforest.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.math.Vector2;

/**
 * Tiles managing class.
 * Map's model: <br>
 * . X---> <br>
 * Y 0 1 2 <br>
 * | 3 4 5 <br>
 * V 6 7 8 <br>
 * @author adamsko
 *
 */
public class TilesMaster implements PickMasterClient {

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
	
	public void insertWorldObject(WorldObject insertObject, Vector2 tilePos) {
		Tile parentTile = tilesContainer.getTileOnPos(tilePos);
		if(parentTile != null) {
			parentTile.insertWorldObject(insertObject);
		}
	}
	
	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {	
		tilesContainer.onInput(inputScreenPos, inputTilesPos);
	}
}