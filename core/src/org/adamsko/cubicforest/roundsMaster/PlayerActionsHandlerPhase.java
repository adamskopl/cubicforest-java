package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.players.PlayerActionsHandler;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Base for classes handling player's actions for concrete phases. By using
 * methods from them only, handlers should build reaction algorithms.
 */
public abstract class PlayerActionsHandlerPhase implements PlayerActionsHandler {

	public PlayerActionsHandlerPhase() {
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void onTileChoice(final Tile tile) {
	}

	@Override
	public void onHeroToolChoice(final WorldObjectType heroToolType) {
	}

	@Override
	public void onOrderChoice(final OrderDecisionDefault orderDecision) {
	}

	@Override
	public void onConfirm() {
	}

	@Override
	public void onCancel() {
	}

}
