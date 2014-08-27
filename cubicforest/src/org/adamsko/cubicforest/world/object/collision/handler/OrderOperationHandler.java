package org.adamsko.cubicforest.world.object.collision.handler;

import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;

public interface OrderOperationHandler {

	void setOrderOperation(OrderOperation orderOperation);

	OrderOperation getOrderOperation();

	void reset();

}
