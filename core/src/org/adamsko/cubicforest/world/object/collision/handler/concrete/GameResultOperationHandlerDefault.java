package org.adamsko.cubicforest.world.object.collision.handler.concrete;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.gameResult.GameResult;
import org.adamsko.cubicforest.world.object.collision.handler.GameResultOperationHandler;

public class GameResultOperationHandlerDefault implements
		GameResultOperationHandler {

	/**
	 * Modified if game is lost or won.
	 */
	private final RoundsMaster roundsMaster;

	public GameResultOperationHandlerDefault(final RoundsMaster roundsMaster) {
		this.roundsMaster = roundsMaster;
	}

	@Override
	public void setGameResult(final GameResult gameResult) {
		roundsMaster.setGameResultSingle(gameResult);
	}

}
