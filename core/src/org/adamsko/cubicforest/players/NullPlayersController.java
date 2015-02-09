package org.adamsko.cubicforest.players;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.tile.Tile;

public class NullPlayersController implements PlayersController {
	private static NullPlayersController instance = null;

	private NullPlayersController() {
	}

	public static NullPlayersController instance() {
		if (instance == null) {
			instance = new NullPlayersController();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public void onGuiEvent(final GuiElementsContainer eventGui) {
	}

	@Override
	public void onTilePicked(final Tile tile) {
	}

	@Override
	public Player getActivePlayer() {
		return NullPlayer.instance();
	}

	@Override
	public void initializeRoundsMaster(final RoundsMaster roundsMaster) {
	}

	@Override
	public void switchPlayerUser() {
	}

	@Override
	public void switchPlayerResolver() {
	}

	@Override
	public void switchPlayerOrderDecisionsReplay() {
	}

}
