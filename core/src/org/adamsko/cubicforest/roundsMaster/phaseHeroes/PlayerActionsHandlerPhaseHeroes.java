package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.roundsMaster.PlayerActionsHandlerPhase;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.NullCubicTile;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

class PlayerActionsHandlerPhaseHeroes extends PlayerActionsHandlerPhase {

	private final PhaseHeroes phaseHeroes;

	PlayerActionsHandlerPhaseHeroes(final PhaseHeroes phaseHeroes) {
		this.phaseHeroes = phaseHeroes;
		ConditionalLog.addObject(this, "PlayerActionsHandlerPhaseHeroes");
		ConditionalLog.setUsage(this, true);
	}

	@Override
	public void onConfirm() {
		if (!phaseHeroes.isOrderInProgress()
				&& !phaseHeroes.getTilePathActive().isNull()) {
			phaseHeroes.orderStarted();
			phaseHeroes.getToolsMaster().heroToolMarkerAccept();
			phaseHeroes.issueOrder(phaseHeroes.getCurrentObject(),
					phaseHeroes.getTilePathActive());
			phaseHeroes.resetTilePathActive();
		}
	}

	@Override
	public void onCancel() {
		phaseHeroes.getToolsMaster().removeHeroToolMarker();
		// highlight tiles
		phaseHeroes.getOrdersMaster().tilePicked(NullCubicTile.instance(),
				false);
		phaseHeroes.getOrdersMaster().highlightTilesOrder();
	};

	@Override
	public void onTileChoice(final Tile tile) {
		// if currently no order is issued
		if (!phaseHeroes.isOrderInProgress()) {
			// create path to chosen tile
			final TilePath tilePath = phaseHeroes.searchTilePath(
					phaseHeroes.getCurrentObject(), tile);

			// check if path is valid for an order
			final boolean tileOrderValid = phaseHeroes.isPathOrderValidObject(
					phaseHeroes.getCurrentObject(), tile, tilePath);

			// set active path
			if (tileOrderValid) {
				phaseHeroes.setTilePathActive(tilePath);
			}

			// remove tool marker
			phaseHeroes.getToolsMaster().removeHeroToolMarker();
			// set chosen tile
			phaseHeroes.getOrdersMaster().tilePicked(tile, tileOrderValid);
			// highlight tiles
			phaseHeroes.getOrdersMaster().highlightTilesOrder();
		}
	}

	@Override
	public void onHeroToolChoice(final WorldObjectType heroToolType) {
		// if currently no order is issued
		if (phaseHeroes.isHeroToolAffordable(heroToolType)) {
			phaseHeroes.getOrdersMaster().toolPicked(heroToolType);
		} else {
			// 'tool not affordable' reaction
		}
	}

}
