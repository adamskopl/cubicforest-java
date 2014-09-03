package org.adamsko.cubicforest.world.tile;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.tile.tilesEvents.NullTilesEventsHandler;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandlerDefault;
import org.adamsko.cubicforest.world.tile.tilesSearcher.TilesSearcher;
import org.adamsko.cubicforest.world.tile.tilesSearcher.TilesSearcherDefault;

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
// FIXME: interface needed
public class TilesMaster implements PickMasterClient, Nullable {

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
		OCCUPANT_STOPS
	}

	// number of tiles (mapSize = 16 -> 4x4 tiles)
	private int mapSize;
	private List<TilesMasterClient> clients;
	private TilesContainer tilesContainer;
	private TilesSearcher tilesSearcher;

	private TilesEventsHandlerDefault tilesEventsHandler;

	/**
	 * For NullTilesMaster
	 */
	TilesMaster() {
	}

	public TilesMaster(final int mapSize) {
		this.mapSize = mapSize;
		this.clients = new ArrayList<TilesMasterClient>();
		this.tilesEventsHandler = NullTilesEventsHandler.instance();
		TilesHelper.setMapSize(mapSize);
		initTiles();
		tilesSearcher = new TilesSearcherDefault(this);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	public void addClient(final TilesMasterClient client) {
		clients.add(client);
	}

	public void initTiles() {

		tilesContainer = new TilesContainer("tiles container", this);

		for (int fIndex = 0; fIndex < mapSize; fIndex++) {
			final Vector2 fCoords = TilesHelper.calcCoords(fIndex);
			fCoords.add(new Vector2(7, -3)); // temporary solution for centering
		}
	}

	public void initTilesEventsHandler(
			final RoundsMaster roundsMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		tilesEventsHandler = new TilesEventsHandlerDefault(roundsMaster,
				collisionVisitorsManagerFactory);
	}

	public TilesContainer getTilesContainer() {
		return tilesContainer;
	}

	public Tile getTileWithObject(final WorldObject object) {
		return tilesContainer.getTileWithObject(object);
	}

	/**
	 * Get tiles adjacent to given tile.
	 */
	public List<Tile> getTilesAdjacent(final Tile tile) {
		return tilesSearcher.getTilesAdjacent(tile);
	}

	/**
	 * Get tiles that are in range of the given tile.
	 */
	public List<Tile> getTilesInRange(final WorldObject object, final int range) {
		final Tile objectTile = tilesContainer.getTileOnPos(object
				.getTilesPos());

		return tilesSearcher.getTilesInRange(objectTile, range);
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
				parentTile.addOccupant(addObject);
			} catch (final Exception e) {
				Gdx.app.error("addWorldObject", e.toString());
			}
		}
	}

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
		tilesContainer.changeTexture(tileToHighlight, texRow, texCol);
	}

	public void highlightTiles(final List<Tile> tilesToHighlight,
			final int texRow, final int texCol) {
		for (final Tile t : tilesToHighlight) {
			tilesContainer.changeTexture(t, texRow, texCol);
		}
	}

	public int occupiedTiles() {
		int occupiedTiles = 0;
		for (final RenderableObject ro : tilesContainer.getTiles()) {
			final Tile t = (Tile) ro;
			if (t.hasOccupant()) {
				occupiedTiles++;
			}
		}
		return occupiedTiles;
	}

	public TilesEventsHandlerDefault getTilesEventsHandler() {
		return tilesEventsHandler;
	}

}