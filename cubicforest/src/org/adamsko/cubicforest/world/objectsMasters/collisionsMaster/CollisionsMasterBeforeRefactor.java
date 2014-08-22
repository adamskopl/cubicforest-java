package org.adamsko.cubicforest.world.objectsMasters.collisionsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.Gdx;

public class CollisionsMasterBeforeRefactor {

	// SINGLETON /////////////////////////////////////
	private static CollisionsMasterBeforeRefactor instance = null;

	public static CollisionsMasterBeforeRefactor instance() {
		if (instance == null) {
			instance = new CollisionsMasterBeforeRefactor();
		}
		return instance;
	}

	private CollisionsMasterBeforeRefactor() {
		clients = new ArrayList<CollisionsMasterClient>();
	}

	// SINGLETON /////////////////////////////////////

	private final List<CollisionsMasterClient> clients;

	public void addClient(final CollisionsMasterClient client) {
		clients.add(client);
	}

	public CollisionResult tileEvent(final TileEvent evenType,
			final Tile eventTile, final WorldObject eventObject)
			throws Exception {

		Boolean orderChanged = false;
		CollisionResult collisionResult = new CollisionResult(null, null);

		if (Tile.occupantsRefactor) {
			for (final WorldObject o : eventTile.getOccupants()) {
				switch (evenType) {
				case OCCUPANT_ENTERS:
					o.accept(eventObject.collision().visitEnter());
					break;

				case OCCUPANT_STOPS:
					o.accept(eventObject.collision().visitStop());
				default:
					break;
				}

			}

		} else {

			/**
			 * Resolve effects of collision for every client.
			 */
			for (final CollisionsMasterClient client : clients) {
				if (client.isTileEventValid(evenType, eventTile)) {

					final CollisionResult clientCollisionResult = client
							.processTileEvent(evenType, eventTile, eventObject);

					/**
					 * Design flaw assumption: only one client is changing
					 * 'collisionResult' value
					 */
					if (!clientCollisionResult.defaultValues()) {
						if (orderChanged) {
							Gdx.app.error("CollisionMaster",
									"order is changed more than one time");
						}
						collisionResult = clientCollisionResult;
						orderChanged = true;
					}
				}
			}
		}
		return collisionResult;
	}
}
