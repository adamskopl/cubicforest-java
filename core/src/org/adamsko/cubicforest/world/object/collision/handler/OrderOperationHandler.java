package org.adamsko.cubicforest.world.object.collision.handler;

import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;

/**
 * Receives and holds {@link OrderOperation}, which indicates how current order
 * should be changed. E.g with {@link #setOrderOperation(OrderOperation)} object
 * with a move order can be stopped. Used when resolving collisions.
 */
public interface OrderOperationHandler {

	/**
	 * Change current order.
	 */
	void setOrderOperation(OrderOperation orderOperation);

	/**
	 * Receive information about how current order should be changed.
	 */
	OrderOperation getOrderOperation();

	/**
	 * Reset to default value, which will not have any effect on order change.
	 */
	void reset();

}
