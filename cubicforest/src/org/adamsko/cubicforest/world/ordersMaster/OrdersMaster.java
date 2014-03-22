package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.roundsMaster.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.TerrainObjectsMaster;
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

}
