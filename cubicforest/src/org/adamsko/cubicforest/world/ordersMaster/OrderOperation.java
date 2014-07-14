package org.adamsko.cubicforest.world.ordersMaster;

public enum OrderOperation {
	/**
	 * Don't change the order.
	 */
	ORDER_CONTINUE,
	ORDER_START,
	/**
	 * Pause the order.
	 */
	ORDER_PAUSE,
	ORDER_RESUME,	
	ORDER_FINISH
}
