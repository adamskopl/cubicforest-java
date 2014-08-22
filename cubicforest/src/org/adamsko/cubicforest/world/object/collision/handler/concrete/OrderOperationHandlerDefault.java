package org.adamsko.cubicforest.world.object.collision.handler.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;

public class OrderOperationHandlerDefault implements OrderOperationHandler {

	private OrderOperation orderOperation;

	public OrderOperationHandlerDefault() {
		orderOperation = OrderOperation.ORDER_CONTINUE;
	}

	@Override
	public OrderOperation getOrderOperation() {
		return orderOperation;
	}

	@Override
	public void setOrderOperation(final OrderOperation orderOperation) {
		this.orderOperation = orderOperation;
	}

}
