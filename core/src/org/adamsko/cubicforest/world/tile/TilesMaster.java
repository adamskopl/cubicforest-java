package org.adamsko.cubicforest.world.tile;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.Portal;
import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandler;
import org.adamsko.cubicforest.world.tile.tilesSearcher.TilesSearcher;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameterFactory;

/**
 * Interface for class managing {@link Tile} objects. Connects
 * {@link WorldObject} objects with {@link Tile} objects.
 * 
 * @author adamsko
 * 
 */
public interface TilesMaster extends PickMasterClient, Nullable {

	/**
	 * Add a client that will be informed about picked tiles.
	 */
	void addPickClient(final TilePickClient client);

	/**
	 * Initialize {@link TilesEventsHandler}
	 * 
	 * @param roundsMaster
	 * @param collisionVisitorsManagerFactory
	 */
	void initTilesEventsHandler(
			final RoundsMaster roundsMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	/**
	 * Get map's size. The maximum horizontal/vertical dimension.
	 */
	int getMapSize();

	/**
	 * Get {@link TilesEventsHandler}
	 */
	TilesEventsHandler getTilesEventsHandler();

	TilesContainer getTilesContainer();

	/**
	 * Associate given {@link WorldObject} object with a {@link Tile} object.
	 * 
	 * @param addObject
	 *            {@link WorldObject} object to be associated with a
	 *            {@link Tile}.
	 */
	void addWorldObject(final WorldObject addObject);

	void addWorldObject(final Portal portal);

	/**
	 * Removes {@link WorldObject} objects from the tile it is occupying.
	 * 
	 * @param removeObject
	 *            object to be removed
	 */
	void removeWorldObject(final WorldObject removeObject);

	/**
	 * Get tile occupied by given object.
	 */
	Tile getTileWithObject(final WorldObject object);

	/**
	 * Get tiles adjacent to given tile.
	 */
	List<Tile> getTilesAdjacent(final Tile tile);

	/**
	 * Get tiles that are in the range of the given tile. <br>
	 * See
	 * {@link TilesSearcher#getTilesInRange(Tile, int, TilesSearchParameter)}
	 */
	List<Tile> getTilesInRange(final WorldObject worldObject, final int range,
			TilesSearchParameter tilesSearchParameter);

	/**
	 * Get tiles, that are away from given distance. <br>
	 * See {@link TilesSearcher#getTilesAway(Tile, int, TilesSearchParameter)}
	 */
	List<Tile> getTilesAway(final WorldObject worldObject, final int distance,
			TilesSearchParameter tilesSearchParameter);

	/**
	 * Get {@link TilesLookController} which allows to change tiles look.
	 */
	TilesLookController getTilesLookController();

	/**
	 * Get {@link TilesSearchParameterFactory} which creates
	 * {@link TilesSearchParameter} objects parameterizing search methods.
	 * 
	 * @return
	 */
	TilesSearchParameterFactory getTilesSearchParameterFactory();

}
