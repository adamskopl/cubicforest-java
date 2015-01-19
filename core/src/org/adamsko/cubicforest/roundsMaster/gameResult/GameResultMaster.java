package org.adamsko.cubicforest.roundsMaster.gameResult;

/**
 * Manages {@link GameResult} value for the game.
 * 
 * @author adamsko
 * 
 */
public interface GameResultMaster {

	/**
	 * Get {@link GameResult}. Used only in
	 * org.adamsko.cubicforest.roundsMaster.gameResult package.
	 */
	GameResult getGameResult();

	/**
	 * Set {@link GameResult}. Used only in
	 * org.adamsko.cubicforest.roundsMaster.gameResult package.
	 */
	void setGameResult(GameResult gameResult);

	/**
	 * Return information, if the game is currently in progress: no result
	 * (lost/won) as . far.
	 * 
	 * @return is the gameplay in progress
	 */
	public boolean isGamePlaying();

	/**
	 * Return information, if the game is currently lost: no gameplay, player
	 * has lost the game.
	 * 
	 * @return has player lost?
	 */
	public boolean isGameLost();

	/**
	 * Return information, if the game is currently won: no gameplay, player has
	 * won the game.
	 * 
	 * @return has player won?
	 */
	public boolean isGameWon();

	/**
	 * Set game stay to 'play' state.
	 */
	public void setGamePlay();

	/**
	 * Set game stay to 'lost' state. It can be changed from 'PLAY' only once
	 * until 'resetGameResult()' is not invoked. The assumption is, that game
	 * result can be changed only once, which prevents overwriting. Prevents
	 * from situation, when e.g. 'win' state is suddenly overwritten by 'lost'
	 * state.
	 */
	public void setGameLost();

	/**
	 * Set game stay to 'won' state.
	 */
	public void setGameWon();

	/**
	 * Invoked when game result is read. From now game result can be changed.
	 */
	void resetGameResult();

}
