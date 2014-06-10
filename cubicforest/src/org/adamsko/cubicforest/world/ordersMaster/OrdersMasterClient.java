package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.world.object.WorldObject;

public interface OrdersMasterClient {

	public void onOrderFinished(OrdersMasterResult result, WorldObject objectWithOrder);
	
}
