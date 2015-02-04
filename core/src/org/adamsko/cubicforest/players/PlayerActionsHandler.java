package org.adamsko.cubicforest.players;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Handles player actions interpreted in {@link Player}. In contrary to
 * {@link Player}, implementing classes receive concrete player actions (like
 * ready order decisions), don't know anything about e.g. gui. Handled actions
 * are results of {@link Player} interpretations.
 */
public interface PlayerActionsHandler extends Nullable {

	/**
	 * Player has chosen a tile.
	 */
	void onTileChoice(final Tile tile);

	/**
	 * Player has chosen a new tool.
	 */
	void onHeroToolChoice(final WorldObjectType heroToolType);

	/**
	 * Player has issued a new order decision.
	 */
	void onOrderChoice(OrderDecisionDefault orderDecision);

	/**
	 * Player sends 'confirm' decision. E.g. 'confirm' button clicked.
	 */
	void onConfirm();

	/**
	 * Player sends 'cancel' decision. E.g. 'cancel' button clicked.
	 */
	void onCancel();

}
