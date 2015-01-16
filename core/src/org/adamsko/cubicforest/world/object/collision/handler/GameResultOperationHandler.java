package org.adamsko.cubicforest.world.object.collision.handler;

import org.adamsko.cubicforest.roundsMaster.gameResult.GameResult;

/**
 * Interface for class responsible for performing operations associated with
 * changing game's result.
 * 
 * @author adamsko
 * 
 */
public interface GameResultOperationHandler {

	void setGameResult(final GameResult gameResult);

}
