package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.roundsMaster.PlayerActionsHandlerPhase;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

public class PlayerActionsHandlerPhaseHeroes extends PlayerActionsHandlerPhase {

	private final PhaseHeroes phaseHeroes;

	public PlayerActionsHandlerPhaseHeroes(final PhaseHeroes phaseHeroes) {
		this.phaseHeroes = phaseHeroes;
	}

	@Override
	public void onConfirm() {
		if (!phaseHeroes.isOrderInProgress()
				&& !phaseHeroes.getTilePathActive().isNull()) {
			phaseHeroes.orderStarted();
			phaseHeroes.issueOrder(phaseHeroes.getCurrentObject(),
					phaseHeroes.getTilePathActive());
			phaseHeroes.resetTilePathActive();
		}
	}

	@Override
	public void onCancel() {
		phaseHeroes.removeHeroToolMarker();
	};

	@Override
	public void onTileChoice(final Tile tile) {
		// if currently no order is issued
		if (!phaseHeroes.isOrderInProgress()) {
			// create path to chosen tile
			final TilePath tilePath = phaseHeroes.searchTilePath(
					phaseHeroes.getCurrentObject(), tile);

			// check if path is valid for an order
			final boolean tilePathOrderValid = phaseHeroes
					.isPathOrderValidObject(phaseHeroes.getCurrentObject(),
							tile, tilePath);

			// highlight tiles
			phaseHeroes.highlightTilesOrder(tile, tilePathOrderValid);

			// if tool is active, add tool marker
			if (phaseHeroes.isHeroToolChosen()) {
				phaseHeroes.addHeroToolMarker(tile);
			}
			// set active path
			if (tilePathOrderValid) {
				phaseHeroes.setTilePathActive(tilePath);
			}
		}
	}

	@Override
	public void onHeroToolChoice(final WorldObjectType heroToolType) {
		if (phaseHeroes.isHeroToolAffordable(heroToolType)) {
			phaseHeroes.chooseHeroTool(heroToolType);
		} else {
			// 'tool not affordable' reaction
		}
	}

}
