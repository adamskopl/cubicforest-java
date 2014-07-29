package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;

public class OrdersMasterResult {
	
	private OrdersMasterPathResult pathResult;
	private CollisionResult collisionResult;
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
	
	public OrdersMasterResult(CollisionResult collisionResult) {
		this();
		this.collisionResult = collisionResult;
		pathResult = OrdersMasterPathResult.ORDER_PATH_FINISHED;
	}
	
	public OrdersMasterResult(OrdersMasterPathResult pathResult) {
		this();
		this.collisionResult = new CollisionResult(null, null);
		this.pathResult = pathResult;
	}

	public OrdersMasterPathResult getPathResult() {
		return pathResult;
	}

	public void setPathResult(OrdersMasterPathResult pathResult) {
		this.pathResult = pathResult;
	}

	public CollisionResult getCollisionResult() {
		return collisionResult;
	}

	public void setCollisionResult(CollisionResult collisionResult) {
		this.collisionResult = collisionResult;
	}

}
