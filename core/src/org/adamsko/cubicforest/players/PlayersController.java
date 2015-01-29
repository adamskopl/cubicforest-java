package org.adamsko.cubicforest.players;

import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.players.decisionOrdersReplay.PlayerDecisionOrdersReplay;
import org.adamsko.cubicforest.players.resolver.MapsResolver;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.tile.TilePickClient;

/**
 * Interface for a class controlling Player implementing classes.
 * 
 * @author adamsko
 * 
 */
public interface PlayersController extends GuiMasterClient, TilePickClient {

	/**
	 * Get player which is currently controlling the game.
	 */
	Player getActivePlayer();

	/**
	 * Choose user of the application as a player.
	 */
	void switchPlayerUser();

	/**
	 * Choose {@link MapsResolver} as a player;
	 */
	void switchPlayerResolver();

	/**
	 * Choose {@link PlayerDecisionOrdersReplay} as a player.
	 */
	void switchPlayerOrderDecisionsReplay();

	/**
	 * {@link PlayersController} is using {@link RoundPhase} phases, they should
	 * be set after initializing in {@link RoundsMaster}.
	 */
	void initializeRoundsMaster(RoundsMaster roundsMaster);

}
