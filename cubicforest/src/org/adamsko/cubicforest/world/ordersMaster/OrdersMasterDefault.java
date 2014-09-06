package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandler;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMasterDefault;

import com.badlogic.gdx.Gdx;

public class OrdersMasterDefault implements OrdersMaster {

	private TilePathsMaster tilePathsMaster;
	private TilesMaster tilesMaster;

	/**
	 * Client which will be informed about the result of the order. Don't know
	 * if assumption about single order will not be changed on any number of
	 * clients.
	 */
	private OrdersMasterClient client = null;

	/**
	 * For NullOrdersMaster
	 */
	public OrdersMasterDefault() {
	}

	public OrdersMasterDefault(final TilesMaster tilesMaster) {

		final TilesEventsHandler tilesEventsHandler = tilesMaster
				.getTilesEventsHandler();

		if (tilesEventsHandler.isNull()) {
			Gdx.app.error("OrdersMasterDefault()",
					"tilesEventsHandler.isNull()");
		}

		tilePathsMaster = new TilePathsMasterDefault(this,
				tilesMaster.getTilesEventsHandler());

		this.tilesMaster = tilesMaster;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void onPathFinished() {
		client.onOrderFinished();
	}

	@Override
	public void startOrder(final WorldObject wanderer, final TilePath path,
			final OrdersMasterClient client) {
		this.client = client;
		tilePathsMaster.startPath(wanderer, path);
	}

}
