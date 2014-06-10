package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResult;

public class OrdersMasterResult {
	
	private OrdersMasterPathResult_e pathResult;
	private InteractionResult interactionResult;
	
	public OrdersMasterResult(InteractionResult interactionResult) {
		this.interactionResult = interactionResult;
		pathResult = OrdersMasterPathResult_e.ORDER_PATH_FINISHED;
	}
	
	public OrdersMasterResult(OrdersMasterPathResult_e pathResult) {
		this.interactionResult = new InteractionResult();
		this.pathResult = pathResult;
	}

	public OrdersMasterPathResult_e getPathResult() {
		return pathResult;
	}

	public void setPathResult(OrdersMasterPathResult_e pathResult) {
		this.pathResult = pathResult;
	}

	public InteractionResult getInteractionResult() {
		return interactionResult;
	}

	public void setInteractionResult(InteractionResult interactionResult) {
		this.interactionResult = interactionResult;
	}

}
