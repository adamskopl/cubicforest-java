package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
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

	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		for (InteractionMasterClient client : clients) {
			if (client.isTileEventValid(evenType, eventTile, eventObject)) {
				client.tileEvent(evenType, eventTile, eventObject);
			}
		}

	}
}
