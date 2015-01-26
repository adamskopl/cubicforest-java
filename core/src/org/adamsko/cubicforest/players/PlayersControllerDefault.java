package org.adamsko.cubicforest.players;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.gui.debug.GuiDebug;
import org.adamsko.cubicforest.gui.debug.GuiElementDebug;
import org.adamsko.cubicforest.gui.heroTools.GuiElementHeroTool;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.levels.GuiElementLevel;
import org.adamsko.cubicforest.gui.levels.GuiLevels;
import org.adamsko.cubicforest.gui.orders.GuiElementOrder;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.gui.resolver.GuiElementResolver;
import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.players.decisionOrdersReplay.PlayerDecisionOrdersReplay;
import org.adamsko.cubicforest.players.resolver.MapsResolver;
import org.adamsko.cubicforest.players.resolver.PlayerMapsResolver;
import org.adamsko.cubicforest.players.user.PlayerUser;
import org.adamsko.cubicforest.roundsMaster.NullRoundsMaster;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

public class PlayersControllerDefault implements PlayersController {

	private RoundsMaster roundsMaster;
	private Player activePlayer;
	private final Player playerUser;
	private final Player playerMapsResolver;
	private final Player playerDecisionOrdersReplay;

	public PlayersControllerDefault(final MapsResolver mapsResolver,
			final TilesMaster tilesMaster) {
		playerUser = new PlayerUser(this);
		playerMapsResolver = new PlayerMapsResolver(this, mapsResolver,
				tilesMaster);
		playerDecisionOrdersReplay = new PlayerDecisionOrdersReplay(this);
		activePlayer = playerUser;
		this.roundsMaster = NullRoundsMaster.instance();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void initializeRoundsMaster(final RoundsMaster roundsMaster) {
		this.roundsMaster = roundsMaster;
		playerUser.initializeRoundsMaster(roundsMaster);
		playerMapsResolver.initializeRoundsMaster(roundsMaster);
		playerDecisionOrdersReplay.initializeRoundsMaster(roundsMaster);
	}

	@Override
	public Player getActivePlayer() {
		return activePlayer;
	}

	@Override
	public void switchPlayerUser() {
		activePlayer = playerUser;
		activePlayer.startControl();
	}

	@Override
	public void switchPlayerResolver() {
		activePlayer = playerMapsResolver;
		activePlayer.startControl();
	}

	@Override
	public void onGuiEvent(final GuiElementsContainer eventGui) {

		if (activePlayer != playerUser) {
			return;
		}
		switch (eventGui.getType()) {
		case GUI_ORDERS:
			guiOrdersClicked((GuiOrders) eventGui);
			break;
		case GUI_HERO_TOOLS:
			guiHeroToolsClicked((GuiHeroTools) eventGui);
			break;
		case GUI_DEBUG:
			guiDebugClicked((GuiDebug) eventGui);
			break;

		case GUI_LEVELS:
			guiLevelsClicked((GuiLevels) eventGui);
			break;

		case GUI_RESOLVER:
			guiResolverClicked((GuiResolver) eventGui);
			break;

		default:
			break;
		}
	}

	private void guiLevelsClicked(final GuiLevels guiLevels) {
		final GuiElementLevel clickedElementLevel = (GuiElementLevel) guiLevels
				.getClickedElement();

		roundsMaster.setMapActive(clickedElementLevel.getLevelIndex());
		roundsMaster.reload();
	}

	private void guiDebugClicked(final GuiDebug guiDebug) {
		final GuiElementDebug clickedElementDebug = (GuiElementDebug) guiDebug
				.getClickedElement();

		switch (clickedElementDebug.getDebugType()) {
		case DEBUG_RELOAD:
			roundsMaster.reload();
			break;

		default:
			break;
		}
	}

	private void guiHeroToolsClicked(final GuiHeroTools guiHeroTools) {
		final GuiElementHeroTool clickedElement = (GuiElementHeroTool) guiHeroTools
				.getClickedElement();
		final WorldObjectType heroToolType = clickedElement.getType();
		activePlayer.onPlayerHeroToolChoice(heroToolType);
	}

	private void guiOrdersClicked(final GuiOrders guiOrders) {
		final GuiElementOrder clickedElement = (GuiElementOrder) guiOrders
				.getClickedElement();

		switch (clickedElement.getOrderType()) {
		case ORDER_ACCEPT:
			activePlayer.onGuiOrderAccept();
			break;
		case ORDER_CANCEL:
			activePlayer.onGuiOrderCancel();
			break;
		default:
			break;
		}
	}

	private void guiResolverClicked(final GuiResolver eventGui) {
		final GuiElementResolver clickedElementResolver = (GuiElementResolver) eventGui
				.getClickedElement();

		switch (clickedElementResolver.getGuiResolverType()) {
		case RESOLVER_START:
			// switch to resolver
			switchPlayerResolver();
			break;

		case RESOLVER_SOLUTION:
			break;

		default:
			break;
		}
	}

	@Override
	public void onTilePicked(final Tile tile) {
		activePlayer.onPlayerTileChoice(tile);
	}

}
