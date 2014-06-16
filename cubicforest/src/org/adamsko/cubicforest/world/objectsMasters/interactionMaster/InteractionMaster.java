package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation_e;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class InteractionMaster {

	private List<InteractionMasterClient> clients;

	public InteractionMaster() {
		clients = new ArrayList<InteractionMasterClient>();
	}

	public void addClient(InteractionMasterClient client) {
		clients.add(client);
	}

	public InteractionResult tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		Boolean orderChanged = false;
		InteractionResult interactionResult = new InteractionResult();

		/**
		 * Resolve effects of interaction for every client.
		 */
		for (InteractionMasterClient client : clients) {
			if (client.isTileEventValid(evenType, eventTile, eventObject)) {
				interactionResult = client.processTileEvent(evenType,
						eventTile, eventObject);
				/**
				 * Design flaw assumption: only one client is changing
				 * 'interactionResult' value
				 */
				if (interactionResult.getOrderOperation() != OrderOperation_e.ORDER_CONTINUE
						|| interactionResult.getObjectOperation() != ObjectOperation_e.OBJECT_NOTHING) {
					if (orderChanged) {
						Gdx.app.error("InteractionMaster",
								"order is changed more than one time");
					}
					orderChanged = true;
				}
			}
		}
		if(interactionResult.getObjectOperation() == ObjectOperation_e.OBJECT_REMOVE) {
			Gdx.app.debug("InteractionMaster", "OBJECT REMOVE");
		}
		return interactionResult;
	}
}
