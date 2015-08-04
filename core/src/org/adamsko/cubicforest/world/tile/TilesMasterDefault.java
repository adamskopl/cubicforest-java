package org.adamsko.cubicforest.world.tile;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.Portal;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookControllerDefault;
import org.adamsko.cubicforest.world.tile.tilesEvents.NullTilesEventsHandler;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandler;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandlerDefault;
import org.adamsko.cubicforest.world.tile.tilesSearcher.TilesSearcher;
import org.adamsko.cubicforest.world.tile.tilesSearcher.TilesSearcherDefault;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TSPFactoryDefatult;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameterFactory;

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
public class TilesMasterDefault implements TilesMaster {

	/**
	 * Event types connected with a {@link Tile}.
	 *
	 * @author adamsko
	 *
	 */
	public enum TileCollisionType {
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
	private List<TilePickClient> clients;
	private TilesContainer tilesContainer;
	private TilesSearcher tilesSearcher;
	private TilesLookController tilesLookController;

	private TilesEventsHandler tilesEventsHandler;
	private TilesSearchParameterFactory tilesSearchParameterFactory;

	/**
	 * For NullTilesMaster
	 */
	TilesMasterDefault() {
	}

	public TilesMasterDefault(final int mapSize,
			final CubicTextureController cubicTextureController,
			final TexturesManager texturesManager) {
		this.mapSize = mapSize;
		this.clients = new ArrayList<TilePickClient>();
		this.tilesEventsHandler = NullTilesEventsHandler.instance();
		TilesHelper.setMapSize(mapSize);
		initTiles(texturesManager);
		tilesSearcher = new TilesSearcherDefault(this);
		tilesLookController = new TilesLookControllerDefault(this,
				getTilesContainer());
		tilesSearchParameterFactory = new TSPFactoryDefatult();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public int getMapSize() {
		return mapSize;
	}

	@Override
	public void addPickClient(final TilePickClient client) {
		clients.add(client);
	}

	@Override
	public void initTilesEventsHandler(
			final RoundsMaster roundsMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		tilesEventsHandler = new TilesEventsHandlerDefault(roundsMaster,
				collisionVisitorsManagerFactory);
	}

	@Override
	public TilesContainer getTilesContainer() {
		return tilesContainer;
	}

	@Override
	public Tile getTileWithObject(final WorldObject object) {
		return tilesContainer.getTileWithObject(object);
	}

	@Override
	public List<Tile> getTilesAdjacent(final Tile tile) {
		return tilesSearcher.getTilesAdjacent(tile);
	}

	@Override
	public List<Tile> getTilesInRange(final WorldObject worldObject,
			final int range, final TilesSearchParameter tilesSearchParameter) {
		final Tile objectTile = tilesContainer.getTileOnPos(worldObject
				.getTilesPos());
		return tilesSearcher.getTilesInRange(objectTile, range,
				tilesSearchParameter);
	}

	@Override
	public List<Tile> getTilesAway(final WorldObject worldObject,
			final int distance, final TilesSearchParameter tilesSearchParameter) {
		final Tile objectTile = tilesContainer.getTileOnPos(worldObject
				.getTilesPos());
		return tilesSearcher.getTilesAway(objectTile, distance,
				tilesSearchParameter);
	}

	@Override
	public void addWorldObject(final WorldObject addObject) {
		final Tile parentTile = tilesContainer.getTileOnPos(addObject
				.getTilesPos());
		if (!parentTile.isNull()) {
			parentTile.addOccupant(addObject);
		}
	}

	@Override
	public void addWorldObject(final Portal portal) {
		final Tile parentTile = tilesContainer.getTileOnPos(portal
				.getTilesPos());
		if (!parentTile.isNull()) {
			parentTile.addPortal(portal);
		}
	}

	@Override
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
	public TilesEventsHandler getTilesEventsHandler() {
		return tilesEventsHandler;
	}

	@Override
	public TilesLookController getTilesLookController() {
		return tilesLookController;
	}

	@Override
	public TilesSearchParameterFactory getTilesSearchParameterFactory() {
		return tilesSearchParameterFactory;
	}

	@Override
	public void onInput(final Vector2 inputScreenPos,
			final Vector2 inputTilesPos) {
		// inform all clients that a tile has been picked
		final Tile clickedTile = tilesContainer.getTileOnPos(inputTilesPos);
		if (!clickedTile.isNull()) {
			for (final TilePickClient client : clients) {
				client.onTilePicked(clickedTile);
			}
		}
	}

	private void initTiles(final TexturesManager texturesManager) {

		tilesContainer = new TilesContainer("tiles container", this);
		tilesContainer.setTexturesManager(texturesManager);
		texturesManager.loadTextures(WorldObjectType.TILE);

		for (int fIndex = 0; fIndex < mapSize; fIndex++) {
			final Vector2 fCoords = TilesHelper.calcCoords(fIndex);
			fCoords.add(new Vector2(7, -3)); // temporary solution for centering
		}
	}

}