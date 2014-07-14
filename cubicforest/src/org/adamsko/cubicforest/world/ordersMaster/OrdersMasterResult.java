package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;

public class OrdersMasterResult {
	
	private OrdersMasterPathResult pathResult;
	private InteractionResult interactionResult;
	private GameResult gameResult;
	
	public GameResult getGameResult() {
		return gameResult;
	}

	public void setGameResult(GameResult gameResult) {
		this.gameResult = gameResult;
	}

	public OrdersMasterResult() {
		this.gameResult = GameResult.GAME_PLAY;
	}
	
	public OrdersMasterResult(InteractionResult interactionResult) {
		this();
		this.interactionResult = interactionResult;
		pathResult = OrdersMasterPathResult.ORDER_PATH_FINISHED;
	}
	
	public OrdersMasterResult(OrdersMasterPathResult pathResult) {
		this();
		this.interactionResult = new InteractionResult(null, null);
		this.pathResult = pathResult;
	}

	public OrdersMasterPathResult getPathResult() {
		return pathResult;
	}

	public void setPathResult(OrdersMasterPathResult pathResult) {
		this.pathResult = pathResult;
	}

	public InteractionResult getInteractionResult() {
		return interactionResult;
	}

	public void setInteractionResult(InteractionResult interactionResult) {
		this.interactionResult = interactionResult;
	}

}
