package org.adamsko.cubicforest.world.ordersMaster;

import java.util.List;

import org.adamsko.cubicforest.roundsMaster.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.TerrainObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathGuide;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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

	private TilePathsMaster tilePathsMaster;
	private HeroesMaster heroesMaster;

	// TerrainObjectsMaster for testing purposes
	private TerrainObjectsMaster testTerrainObjectsMaster;

	private Boolean tempTestStarted;
	private Vector2 tempTargetPos;

	private TilesMaster tilesMaster;

	/**
	 * Client which will be informed about the result of the order. Don't know
	 * if assumption about single order will not be changed on any number of
	 * clients.
	 */
	private OrdersMasterClient client = null;

	public void tempSetTerrainObjectsMaster(TerrainObjectsMaster tom) {
		this.testTerrainObjectsMaster = tom;
	}

	public OrdersMaster(TilesMaster tilesMaster, HeroesMaster heroesMaster) {
		tilePathsMaster = new TilePathsMaster(this, tilesMaster);
		this.heroesMaster = heroesMaster;
		this.tilesMaster = tilesMaster;
		tempTestStarted = false;
		tempTargetPos = new Vector2();
	}

	/**
	 * Invoked by {@link TilePathsMaster} object. Path is finished:
	 * 
	 * @param wanderer
	 *            {@link WorldObject} which was guided by one of the
	 *            {@link TilePathsMaster} object {@link TilePathGuide} objects
	 */
	public void onPathFinished(OrdersMasterResult_e result, WorldObject wanderer) {
		client.onOrderFinished(result, wanderer);
	}

	/**
	 * @param wanderer
	 * @param destinationTile
	 * @param client
	 *            client to be informed about the order's result
	 */
	public void startOrder(WorldObject wanderer, Tile destinationTile,
			OrdersMasterClient client) {
		this.client = client;
		tilePathsMaster.startPath(wanderer, destinationTile);
	}

	public void startOrder(WorldObject wanderer, TilePath path,
			OrdersMasterClient client) {
		this.client = client;
		tilePathsMaster.startPath(wanderer, path);
	}

	/**
	 * Perform operation on present order. Function invoked after the chain of
	 * functions invoked as a result of tilesMaster.event().tileEvent() in
	 * TilePathGuide
	 * 
	 * @param wanderer
	 * @param operation 
	 */
	public void orderOperation(WorldObject wanderer, OrderOperation_e operation) {
		tilePathsMaster.pathOperation(wanderer, operation);
	}

	/**
	 * Highlight tiles which are in object's range
	 * 
	 * @param object
	 */
	public void highlightTilesObjectRange(WorldObject object) {
		List<Tile> tilesInRange = tilesMaster.getTilesInRange(object,
				object.getSpeed(), false);
		tilesMaster.highlightTiles(tilesInRange, 1, 1);
	}

	public void unhighlightTilesObjectRange(WorldObject object) {
		List<Tile> tilesInRange = tilesMaster.getTilesInRange(object,
				object.getSpeed(), false);
		tilesMaster.highlightTiles(tilesInRange, 0, 0);
	}

}
