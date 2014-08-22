package org.adamsko.cubicforest.world.object.collision.master.concrete;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.object.collision.master.GameResultOperationHandler;

public class GameResultOperationHandlerDefault implements
		GameResultOperationHandler {

	/**
	 * Modified if game is lost or won.
	 */
	private GameResult gameResult;

	@Override
	public void setGameResult(final GameResult gameResult) {
		this.gameResult = gameResult;
	}

	@Override
	public GameResult getGameResult() {
		return gameResult;
	}

}
