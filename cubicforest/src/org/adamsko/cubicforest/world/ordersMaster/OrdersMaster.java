package org.adamsko.cubicforest.world.ordersMaster;

import java.util.List;

import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathGuide;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * Manages orders (movement for now) for objects from different
 * {@link PhaseOrderableObjects} client objects. Handles only one client for
 * now.
 * 
 * TODO Can be easily upgrade to any number of clients.
 * 
 * @author adamsko
 * 
 */
public class OrdersMaster {

	private final TilePathsMaster tilePathsMaster;
	private final TilesMaster tilesMaster;

	/**
	 * Client which will be informed about the result of the order. Don't know
	 * if assumption about single order will not be changed on any number of
	 * clients.
	 */
	private OrdersMasterClient client = null;

	public OrdersMaster(final TilesMaster tilesMaster,
			final HeroesMaster heroesMaster, final EnemiesMaster enemiesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final GatherCubesMaster gatherCubesMaster) {

		tilePathsMaster = new TilePathsMaster(this, tilesMaster);

		this.tilesMaster = tilesMaster;
	}

	/**
	 * Invoked by {@link TilePathsMaster} object. Path is finished:
	 * 
	 * @param wanderer
	 *            {@link WorldObject} which was guided by one of the
	 *            {@link TilePathsMaster} object {@link TilePathGuide} objects
	 */
	public void onPathFinished() {
		client.onOrderFinished();
	}

	/**
	 * @param wanderer
	 * @param destinationTile
	 * @param client
	 *            client to be informed about the order's result
	 */
	public void startOrder(final WorldObject wanderer, final TilePath path,
			final OrdersMasterClient client) {
		this.client = client;
		tilePathsMaster.startPath(wanderer, path);
	}

	/**
	 * Highlight tiles which are in object's range
	 * 
	 * @param object
	 */
	public void highlightTilesObjectRange(final WorldObject object) {
		final List<Tile> tilesInRange = tilesMaster.getTilesInRange(object,
				object.getSpeed(), false);
		tilesMaster.highlightTiles(tilesInRange, 1, 1);
	}

	public void unhighlightTilesObjectRange(final WorldObject object) {
		final List<Tile> tilesInRange = tilesMaster.getTilesInRange(object,
				object.getSpeed(), false);
		tilesMaster.highlightTiles(tilesInRange, 0, 0);
	}

}
