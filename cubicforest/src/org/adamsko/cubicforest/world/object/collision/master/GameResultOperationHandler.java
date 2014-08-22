package org.adamsko.cubicforest.world.object.collision.master;

import org.adamsko.cubicforest.roundsMaster.GameResult;

public interface GameResultOperationHandler {

	void setGameResult(final GameResult gameResult);

	GameResult getGameResult();

}
