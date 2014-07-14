package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import java.util.ArrayList;
import java.util.List;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.Gdx;

public class InteractionMaster {

	private List<InteractionMasterClient> clients;

	public InteractionMaster() {
		clients = new ArrayList<InteractionMasterClient>();
	}

	public void addClient(InteractionMasterClient client) {
		clients.add(client);
	}

	public InteractionResult tileEvent(TileEvent evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		Boolean orderChanged = false;
		InteractionResult interactionResult = new InteractionResult(null, null);

		/**
		 * Resolve effects of interaction for every client.
		 */
		for (InteractionMasterClient client : clients) {
			if (client.isTileEventValid(evenType, eventTile, eventObject)) {

				InteractionResult clientInteractionResult = client
						.processTileEvent(evenType, eventTile, eventObject);
				
				/**
				 * Design flaw assumption: only one client is changing
				 * 'interactionResult' value
				 */
				if (!clientInteractionResult.defaultValues()) {
					if (orderChanged) {
						Gdx.app.error("InteractionMaster",
								"order is changed more than one time");
					}
					interactionResult = clientInteractionResult;
					orderChanged = true;
				}
			}
		}		
		return interactionResult;
	}
}
