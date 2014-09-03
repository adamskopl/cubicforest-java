package org.adamsko.cubicforest.world.ordersMaster;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandlerDefault;
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

		final TilesEventsHandlerDefault tilesEventsHandler = tilesMaster
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

	@Override
	public void highlightTilesObjectRange(final WorldObject object) {
		final List<Tile> tilesInRange = tilesMaster.getTilesInRange(object,
				object.getSpeed());
		tilesMaster.highlightTiles(tilesInRange, 1, 1);
	}

	@Override
	public void unhighlightTilesObjectRange(final WorldObject object) {
		final List<Tile> tilesInRange = tilesMaster.getTilesInRange(object,
				object.getSpeed());
		tilesMaster.highlightTiles(tilesInRange, 0, 0);
	}

}
