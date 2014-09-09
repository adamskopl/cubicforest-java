package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearchersMaster;

public class PhaseEnemies extends PhaseOrderableObjects {

	private final HeroesHelper heroesHelper;

	public PhaseEnemies(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final OrdersMaster ordersMaster,
			final TilePathSearchersMaster tilePathSearchersMaster,
			final TilesLookController tilesLookController) {
		super(worldObjectsMastersContainer.getEnemiesMaster(), ordersMaster,
				tilesLookController, "PhaseEnemies");

		heroesHelper = new HeroesHelper(
				worldObjectsMastersContainer.getHeroesMaster(),
				tilePathSearchersMaster);
	}

	@Override
	public void onTilePicked(final Tile tile) {
	}

	@Override
	public void startPhase() {
		moveNextEnemy();
	}

	private void moveNextEnemy() {

		nextObject();
		final WorldObject activeEnemy = currentObject();

		if (activeEnemy.isNull()) {
			return;
		}

		final TilePath shortestPathTileAdjacentHero = heroesHelper
				.searchPathShortestHero(activeEnemy);

		if (shortestPathTileAdjacentHero == null
				|| shortestPathTileAdjacentHero.length() == 0) {

			onOrderFinished();

		} else {
			// shorten path to enemy's speed
			shortestPathTileAdjacentHero.shortenPath(activeEnemy.getSpeed());
			ordersMaster.startOrder(activeEnemy, shortestPathTileAdjacentHero,
					this);
		}
	}

	@Override
	public void onOrderFinished() {

		if (roundsMaster.getGameResult() == GameResult.GAME_LOST) {
			roundsMaster.reload();
			roundsMaster.resetGameResult();
			return;
		}

		if (isCurrentObjectLast()) {
			try {
				phaseIsOver(this);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			return;
		}
		removeDeadObjects();
		moveNextEnemy();

	}

	@Override
	public void onGuiEvent(final GuiElementsContainer eventGui) {
	}

}
