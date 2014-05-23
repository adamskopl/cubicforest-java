package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class InteractionMaster {

	private List<InteractionMasterClient> clients;
	private RoundsMaster roundsMaster;

	public InteractionMaster(RoundsMaster roundsMaster) {
		clients = new ArrayList<InteractionMasterClient>();
		this.roundsMaster = roundsMaster;
	}

	public void addClient(InteractionMasterClient client) {
		clients.add(client);
	}

	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		Boolean orderChanged = false;
		OrderOperation_e orderChange = OrderOperation_e.ORDER_CONTINUE;

		for (InteractionMasterClient client : clients) {
			if (client.isTileEventValid(evenType, eventTile, eventObject)) {
				orderChange = client.processTileEvent(
						evenType, eventTile, eventObject);
				if (orderChange != OrderOperation_e.ORDER_CONTINUE) {
					if (orderChanged) {
						Gdx.app.error("InteractionMaster",
								"order is changed more than one time");
					}
					orderChanged = true; 
				}
			}
		}
		if (orderChanged) {
			roundsMaster.orderOperation(orderChange);
		}
	}

	/**
	 * As a result of tile events, change actually issued orders.
	 * 
	 * @param orderOperation
	 */
	private void orderOperation(OrderOperation_e orderOperation) {
		roundsMaster.orderOperation(orderOperation);
	}
}
