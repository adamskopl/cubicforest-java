package org.adamsko.cubicforest.players.user;

import org.adamsko.cubicforest.players.PlayerBase;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Player: user of the application. Issuing decisions by input.
 * 
 * @author adamsko
 * 
 */
public class PlayerUser extends PlayerBase {

	public PlayerUser() {
		super();
	}

	@Override
	public void initializeRoundsMaster(final RoundsMaster roundsMaster) {
		super.initializeRoundsMaster(roundsMaster);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void startControl() {
		roundsMaster.getCurrentPhase().setActivePlayer(this);
	}

	@Override
	public void makeNextDecision() {
	}

	@Override
	public void onPlayerTileChoice(final Tile tile) {
		roundsMaster.getCurrentPhase().getPlayerActionsHandler()
				.onTileChoice(tile);
	}

	@Override
	public void onPlayerHeroToolChoice(final WorldObjectType heroToolType) {
		roundsMaster.getCurrentPhase().getPlayerActionsHandler()
				.onHeroToolChoice(heroToolType);
	}

	@Override
	public void onGuiOrderAccept() {
		roundsMaster.getCurrentPhase().getPlayerActionsHandler().onConfirm();
	}

	@Override
	public void onGuiOrderCancel() {
		roundsMaster.getCurrentPhase().getPlayerActionsHandler().onCancel();
	}

}
