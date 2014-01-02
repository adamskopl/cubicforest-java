package org.adamsko.cubicforest.pickmaster;

import java.util.List;

public class PickMaster {
	private List<PickMasterClient> clients;
	
	public void addClient(PickMasterClient client) {
		clients.add(client);
	}
}
