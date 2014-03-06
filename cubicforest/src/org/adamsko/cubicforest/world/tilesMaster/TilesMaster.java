package org.adamsko.cubicforest.world.tilesMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.pickmaster.PickMaster;
import org.adamsko.cubicforest.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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
	private List<TilesMasterClient> clients;
	private TilesContainer tilesContainer;
	private OrdersMaster ordersMaster;
	
	public TilesMaster(int mapSize) {
		this.mapSize = mapSize;
		clients = new ArrayList<TilesMasterClient>();
		TilesHelper.setMapSize(mapSize);
		initTiles();
	}
	
	public void addClient(TilesMasterClient client) {
		clients.add(client);
	}
	
	public void initTiles() {
		tilesContainer = new TilesContainer(this);
		for(int fIndex = 0; fIndex < mapSize; fIndex++) {
			if(TilesHelper.isTileonTestMap(fIndex)) {continue;}			
			Vector2 fCoords = TilesHelper.calcCoords(fIndex);
			fCoords.add(new Vector2(7, -3)); // temporary solution for centering view
			tilesContainer.addTile(fCoords);
		}
	}
	
	public TilesContainer getTilesContainer() {
		return tilesContainer;
	}
	
	/**
	 * Get tiles adjacent to given tile. 
	 * 
	 * @param tile
	 * @return
	 */
	public List<Tile> getTilesAdjacent(Tile tile) {
		List<Tile> adjTiles = new ArrayList<Tile>();
		List<Vector2> adjPositions = new ArrayList<Vector2>();
		adjPositions.add(new Vector2(-1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, 1.0f));
		adjPositions.add(new Vector2(1.0f, 0.0f));
		adjPositions.add(new Vector2(0.0f, -1.0f));
		for(Vector2 adjPos : adjPositions) {
			adjPos.add(tile.getTilesPos());
			Tile adjTile = tilesContainer.getTileOnPos(adjPos);
			if(adjTile==null)continue;
			adjTiles.add(adjTile);
		}
		return adjTiles;
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
			try {
				parentTile.insertWorldObject(insertObject);
				tilesContainer.testHighlightTile(parentTile, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void occupantLeftTile(Tile tileLeft, Tile tileEntered) throws Exception {
		WorldObject occupant = tileLeft.takeOutObject();
		tilesContainer.testHighlightTile(tileLeft, 0);
		
		tileEntered.insertWorldObject(occupant);
		tilesContainer.testHighlightTile(tileEntered, 1);
	}
	
	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {	
		Tile clickedTile = tilesContainer.getTileOnPos(inputTilesPos);
		if(clickedTile != null) {
			for(TilesMasterClient client : clients) {
				client.onTileEvent(clickedTile, TileEvent_e.TILE_PICKED);
			}
			
			Gdx.app.log(
					"TilesMaster onInput",
					Float.toString(clickedTile.getTilesPosX()) + ", "
							+ Float.toString(clickedTile.getTilesPosY()));
			
			// FIXME: TilesMasterClient code conversion needed
			tilesContainer.testHighlightTile(clickedTile, 2);
			// FIXME: TilesMasterClient code conversion needed
			clickedTile.handleTileEvent(TileEvent_e.TILE_PICKED);
		}
	}
}