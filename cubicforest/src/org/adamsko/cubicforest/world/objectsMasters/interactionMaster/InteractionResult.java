package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation_e;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;

/**
 * Defines the result of one interatcion
 * 
 * @author adamsko
 * 
 */
public class InteractionResult {

	/**
	 * How the order of active object (guided by TilePathGuide right now) shoul
	 * be modified?
	 */
	private OrderOperation_e orderOperation;
	/**
	 * How the active object should be modified? Active object: object
	 * interacting witch checked object. E.g enemy - heroTool
	 */
	private ObjectOperation_e interactingObjectOperation;

	/**
	 * How the tiled object should me modified? E.g how heroTool should me
	 * modified, when enemy is interacting with it?
	 */
	private ObjectOperation_e tileObjectOperation;

	public ObjectOperation_e getTileObjectOperation() {
		return tileObjectOperation;
	}

	public void setTileObjectOperation(ObjectOperation_e tileObjectOperation) {
		this.tileObjectOperation = tileObjectOperation;
	}

	public InteractionResult() {
		orderOperation = OrderOperation_e.ORDER_CONTINUE;
		interactingObjectOperation = ObjectOperation_e.OBJECT_NOTHING;
		tileObjectOperation = ObjectOperation_e.OBJECT_NOTHING;
	}

	public InteractionResult(OrderOperation_e orderOperation) {
		this.orderOperation = orderOperation;
	}

	public InteractionResult(ObjectOperation_e objectOperation) {
		this.interactingObjectOperation = objectOperation;
	}

	public InteractionResult(OrderOperation_e orderOperation,
			ObjectOperation_e objectOperation) {
		this.orderOperation = orderOperation;
		this.interactingObjectOperation = objectOperation;
	}

	public OrderOperation_e getOrderOperation() {
		return orderOperation;
	}

	public void setOrderOperation(OrderOperation_e orderOperation) {
		this.orderOperation = orderOperation;
	}

	public ObjectOperation_e getObjectOperation() {
		return interactingObjectOperation;
	}

	public void setObjectOperation(ObjectOperation_e objectOperation) {
		this.interactingObjectOperation = objectOperation;
	}

}
