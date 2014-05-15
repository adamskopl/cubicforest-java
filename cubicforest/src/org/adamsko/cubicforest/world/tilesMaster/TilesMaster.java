package org.adamsko.cubicforest.world.tilesMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.tilesMaster.tilesEvents.TilesEventsMaster;
import org.adamsko.cubicforest.world.tilesMaster.tilesSearcher.TilesSearcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

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
		TILE_PICKED,
		/**
		 * Occupant is leaving a tile.
		 */
		OCCUPANT_LEAVES,
		/**
		 * Occupant enters a tile.
		 */
		OCCUPANT_ENTERS,
		/**
		 * Occupant reaches center of the tile and continues movement.
		 */
		OCCUPANT_PASSES,
		/**
		 * Occupant ends his movement on a tile.
		 */
		OCCUPANT_STOPS
	}

	// number of tiles (mapSize = 16 -> 4x4 tiles)
	private int mapSize;
	private List<TilesMasterClient> clients;
	private TilesContainer tilesContainer;
	private MapsLoader mapsLoader;

	private TilesEventsMaster tilesEventsMaster;

	public TilesMaster(MapsLoader mapsLoader, int mapSize) {
		this.mapSize = mapSize;
		this.mapsLoader = mapsLoader;
		clients = new ArrayList<TilesMasterClient>();
		TilesHelper.setMapSize(mapSize);
		initTiles();
	}

	public void addClient(TilesMasterClient client) {
		clients.add(client);
	}

	public void setInteractionMaster(InteractionMaster interactionMaster) {
		tilesEventsMaster.setInteractionMaster(interactionMaster);
	}

	public void initTiles() {

		tilesContainer = new TilesContainer("tiles container", mapsLoader, this);
		tilesContainer.loadMapObjects(mapsLoader);

		tilesEventsMaster = new TilesEventsMaster(tilesContainer);

		for (int fIndex = 0; fIndex < mapSize; fIndex++) {
			if (TilesHelper.isTileonTestMap(fIndex)) {
				continue;
			}
			Vector2 fCoords = TilesHelper.calcCoords(fIndex);
			fCoords.add(new Vector2(7, -3)); // temporary solution for centering
												// view
												// tilesContainer.addTile(fCoords);
		}
	}

	public TilesContainer getTilesContainer() {
		return tilesContainer;
	}

	public Tile getTileWithObject(WorldObject object) {
		return tilesContainer.getTileWithObject(object);
	}

	/**
	 * Get tiles adjacent to given tile.
	 * 
	 * @param tile
	 * @param getOccupied
	 *            indicates if occupied tiles should be considered
	 * @return
	 */
	public List<Tile> getTilesAdjacent(Tile tile, Boolean getOccupied) {
		return TilesSearcher
				.getTilesAdjacent(tile, tilesContainer, getOccupied);
	}

	/**
	 * Get tiles that are in range of the given tile.
	 * 
	 * @param object
	 * @param range
	 * @param getOccupied
	 *            indicates if occupied tiles should be considered (false ==
	 *            don't consider)
	 * @return
	 */
	public List<Tile> getTilesInRange(WorldObject object, int range,
			Boolean getOccupied) {
		Tile objectTile = tilesContainer.getTileOnPos(object.getTilesPos());

		return TilesSearcher.getTilesInRange(objectTile, range, getOccupied);
	}

	/**
	 * Associate given {@link WorldObject} object with a {@link Tile} object.
	 * 
	 * @param addObject
	 *            {@link WorldObject} object to be associated with a
	 *            {@link Tile}.
	 */
	public void addWorldObject(WorldObject addObject) {
		Tile parentTile = tilesContainer.getTileOnPos(addObject.getTilesPos());
		if (parentTile != null) {
			try {
				parentTile.insertObject(addObject);
				switch (addObject.getWorldType()) {
				case OBJECT_ENTITY:
					tilesContainer.testHighlightTile(parentTile, 0, 1);
					break;
				case OBJECT_ITEM:
					break;
				case OBJECT_TERRAIN:
					tilesContainer.testHighlightTile(parentTile, 0, 1);
					break;
				default:
					Gdx.app.error("addWorldObject " + addObject.getName(),
							"unknown");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param removeObject
	 * @throws Exception
	 */
	public void removeWorldObject(WorldObject removeObject) throws Exception {
		Tile parentTile = tilesContainer.getTileOnPos(removeObject
				.getTilesPos());
		if (parentTile != null) {
			switch (removeObject.getWorldType()) {
			case OBJECT_ENTITY:
				WorldObject removedOccupant = parentTile.occupantLeaves();
				if (removeObject != removedOccupant) {
					throw new Exception(
							"removeWorldObject removeObject != removedOccupant");
				}
				break;
			case OBJECT_ITEM:
				WorldObject removedItem = parentTile.itemLeaves();
				if (removeObject != removedItem) {
					throw new Exception(
							"removeWorldObject removeObject != removedOccupant");
				}
				break;
			case OBJECT_TERRAIN:

				break;
			default:
				throw new Exception("removeWorldObject unsupported type");
			}
		}
	}

	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {
		Tile clickedTile = tilesContainer.getTileOnPos(inputTilesPos);
		if (clickedTile != null) {
			for (TilesMasterClient client : clients) {
				client.onTileEvent(clickedTile, TileEvent_e.TILE_PICKED);
			}
		}
	}

	public void clearTilesLabels() {
		tilesContainer.clearTilesLabels();
	}

	public void highlightTile(Tile tileToHighlight, int texRow, int texCol) {
		tilesContainer.testHighlightTile(tileToHighlight, texRow, texCol);
	}

	public void highlightTiles(List<Tile> tilesToHighlight, int texRow,
			int texCol) {
		for (Tile t : tilesToHighlight) {
			tilesContainer.testHighlightTile(t, texRow, texCol);
		}
	}

	public TilesEventsMaster event() {
		return tilesEventsMaster;
	}

}