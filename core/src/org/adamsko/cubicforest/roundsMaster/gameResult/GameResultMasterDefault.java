package org.adamsko.cubicforest.roundsMaster.gameResult;

public class GameResultMasterDefault implements GameResultMaster {

	private GameResult gameResult;

	public GameResultMasterDefault() {
		setGamePlay();
	}

	public GameResultMasterDefault(final GameResultMaster gameResultMaster) {
		this.gameResult = gameResultMaster.getGameResult();
	}

	@Override
	public GameResult getGameResult() {
		return gameResult;
	}

	@Override
	public void setGameResult(final GameResult gameResult) {
		this.gameResult = gameResult;
	}

	@Override
	public boolean isGamePlaying() {
		return gameResult == GameResult.GAME_PLAY;
	}

	@Override
	public boolean isGameLost() {
		return gameResult == GameResult.GAME_LOST;
	}

	@Override
	public boolean isGameWon() {
		return gameResult == GameResult.GAME_WON;
	}

	@Override
	public void setGamePlay() {
		setNewGameState(GameResult.GAME_PLAY);
	}

	@Override
	public void setGameLost() {
		// state can be changed to 'lost' only from state 'play'
		if (isGamePlaying()) {
			setNewGameState(GameResult.GAME_LOST);
		}
	};

	@Override
	public void setGameWon() {
		// state can be changed to 'won' only from state 'play'
		if (isGamePlaying()) {
			setNewGameState(GameResult.GAME_WON);
		}
	}

	@Override
	public void resetGameResult() {
		this.gameResult = GameResult.GAME_PLAY;
	}

	private void setNewGameState(final GameResult newGameResult) {
		this.gameResult = newGameResult;
	}

}
