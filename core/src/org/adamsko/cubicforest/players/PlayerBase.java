package org.adamsko.cubicforest.players;

import org.adamsko.cubicforest.roundsMaster.NullRoundsMaster;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Base for Player classes. Implements common behavior for all players.
 * 
 * @author adamsko
 * 
 */
public abstract class PlayerBase implements Player {

	private final PlayersController playersController;
	protected RoundsMaster roundsMaster;

	@Override
	public boolean isNull() {
		return false;
	}

	public PlayerBase(final PlayersController playersController) {
		this.playersController = playersController;
		this.roundsMaster = NullRoundsMaster.instance();
	}

	@Override
	public PlayersController getPlayersController() {
		return playersController;
	}

	@Override
	public void initializeRoundsMaster(final RoundsMaster roundsMaster) {
		this.roundsMaster = roundsMaster;
	}

	@Override
	public void makeNextDecision() {
	}

	@Override
	public void onPlayerTileChoice(final Tile tile) {
	}

	@Override
	public void onGuiHeroToolsChoice(final WorldObjectType heroToolType) {
	}

	@Override
	public void onGuiLevelsClicked() {
	}

	@Override
	public void onGuiOrderAccept() {
	};

	@Override
	public void onGuiOrderCancel() {
	}

	@Override
	public void onGuiReloadClicked() {
	};

	@Override
	public void onGuiResolverStartClicked() {
	}

	@Override
	public void onPlayerHeroToolChoice(final WorldObjectType heroToolType) {
	}

}
