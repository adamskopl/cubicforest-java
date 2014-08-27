package org.adamsko.cubicforest.world.tile;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandler;
import org.adamsko.cubicforest.world.tile.tilesSearcher.TilesSearcher;

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
	public enum TileEvent {
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
		OCCUPANT_STOPS,
		/**
		 * Occupant stays on a tile (no movement).
		 */
		OCCUPANT_STAYS
	}

	// number of tiles (mapSize = 16 -> 4x4 tiles)
	private final int mapSize;
	private final List<TilesMasterClient> clients;
	private TilesContainer tilesContainer;

	private TilesEventsHandler tilesEventsHandler;

	public TilesMaster(final int mapSize, final RoundsMaster roundsMaster) {
		this.mapSize = mapSize;
		clients = new ArrayList<TilesMasterClient>();
		TilesHelper.setMapSize(mapSize);
		initTiles(roundsMaster);
	}

	public void addClient(final TilesMasterClient client) {
		clients.add(client);
	}

	public void initTiles(final RoundsMaster roundsMaster) {

		tilesContainer = new TilesContainer("tiles container", this);
		tilesEventsHandler = new TilesEventsHandler(tilesContainer,
				roundsMaster);

		for (int fIndex = 0; fIndex < mapSize; fIndex++) {
			final Vector2 fCoords = TilesHelper.calcCoords(fIndex);
			fCoords.add(new Vector2(7, -3)); // temporary solution for centering
												// view
												// tilesContainer.addTile(fCoords);
		}
	}

	public TilesContainer getTilesContainer() {
		return tilesContainer;
	}

	public Tile getTileWithObject(final WorldObject object) {
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
	public List<Tile> getTilesAdjacent(final Tile tile,
			final Boolean getOccupied) {
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
	public List<Tile> getTilesInRange(final WorldObject object,
			final int range, final Boolean getOccupied) {
		final Tile objectTile = tilesContainer.getTileOnPos(object
				.getTilesPos());

		return TilesSearcher.getTilesInRange(objectTile, range, getOccupied);
	}

	/**
	 * Associate given {@link WorldObject} object with a {@link Tile} object.
	 * 
	 * @param addObject
	 *            {@link WorldObject} object to be associated with a
	 *            {@link Tile}.
	 */
	public void addWorldObject(final WorldObject addObject) {
		final Tile parentTile = tilesContainer.getTileOnPos(addObject
				.getTilesPos());
		if (!parentTile.isNull()) {
			try {
				parentTile.addOccupant(addObject, false);
				switch (addObject.getType()) {
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
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param removeObject
	 * @throws Exception
	 */
	public void removeWorldObject(final WorldObject removeObject) {
		final Tile parentTile = tilesContainer.getTileOnPos(removeObject
				.getTilesPos());

		if (!parentTile.isNull()) {
			parentTile.removeOccupant(removeObject);
		} else {
			Gdx.app.error("removeWorldObject() " + removeObject.getName(),
					"no tile");
		}
	}

	@Override
	public void onInput(final Vector2 inputScreenPos,
			final Vector2 inputTilesPos) {
		final Tile clickedTile = tilesContainer.getTileOnPos(inputTilesPos);
		if (!clickedTile.isNull()) {
			for (final TilesMasterClient client : clients) {
				client.onTileEvent(clickedTile, TileEvent.TILE_PICKED);
			}
		}
	}

	public void clearTilesLabels() {
		tilesContainer.clearTilesLabels();
	}

	public void highlightTile(final Tile tileToHighlight, final int texRow,
			final int texCol) {
		tilesContainer.testHighlightTile(tileToHighlight, texRow, texCol);
	}

	public void highlightTiles(final List<Tile> tilesToHighlight,
			final int texRow, final int texCol) {
		for (final Tile t : tilesToHighlight) {
			tilesContainer.testHighlightTile(t, texRow, texCol);
		}
	}

	public int occupiedTiles() {
		int occupiedTiles = 0;
		for (final RenderableObject ro : tilesContainer.getTiles()) {
			final Tile t = (Tile) ro;

			if (Tile.occupantsRefactor) {
				if (t.hasOccupant2()) {
					occupiedTiles++;
				}
			} else {
				if (t.hasOccupant()) {
					occupiedTiles++;
				}
			}
		}
		return occupiedTiles;
	}

	public TilesEventsHandler getTilesEventsHandler() {
		return tilesEventsHandler;
	}

}