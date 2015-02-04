package org.adamsko.cubicforest.players;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Player is controlling the game. It could be e.g. a user of the application or
 * a class resolving levels. Implementing class should handle every player's
 * action. In contrary to {@link PlayerActionsHandler} implementing class role
 * is to handle player's actions like gui clicks, interpret them and pass to
 * {@link PlayerActionsHandler} concrete player actions. If a player is an
 * resolving class, it passes ready order decisions.
 */
public interface Player extends Nullable {

	PlayersController getPlayersController();

	/**
	 * Start control.
	 */
	void startControl();

	/**
	 * Player is using {@link RoundPhase}: initialize variables using
	 * {@link RoundsMaster}'s phaes.
	 */
	void initializeRoundsMaster(RoundsMaster roundsMaster);

	/**
	 * Player is allowed to perform next decision. E.g heroes phase has started
	 * or one of the heroes has ended its movement.
	 */
	void makeNextDecision();

	/**
	 * Invoked when game's victory conditions are met.
	 */
	void onVictoryConditionsMet();

	/**
	 * Reaction on player's tile choice.
	 */
	void onPlayerTileChoice(final Tile tile);

	void onPlayerHeroToolChoice(final WorldObjectType heroToolType);

	void onGuiOrderAccept();

	void onGuiOrderCancel();

	void onGuiHeroToolsChoice(WorldObjectType heroToolType);

	void onGuiReloadClicked();

	void onGuiResolverStartClicked();

	void onGuiLevelsClicked();

}
