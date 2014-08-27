package org.adamsko.cubicforest.world.object.collision.handler.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;

public class OrderOperationHandlerDefault implements OrderOperationHandler {

	private OrderOperation orderOperation;

	public OrderOperationHandlerDefault() {
		reset();
	}

	@Override
	public OrderOperation getOrderOperation() {
		return orderOperation;
	}

	@Override
	public void setOrderOperation(final OrderOperation orderOperation) {
		this.orderOperation = orderOperation;
	}

	@Override
	public void reset() {
		orderOperation = OrderOperation.ORDER_CONTINUE;
	}

}
