package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
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

		final TilePath shortestPathTileHero = heroesHelper
				.searchPathShortestHero(activeEnemy);

		if (shortestPathTileHero == null || shortestPathTileHero.length() == 0) {

			onOrderFinished();

		} else {
			// shorten path to enemy's speed
			shortestPathTileHero.shortenPath(activeEnemy.getSpeed());
			ordersMaster.startOrder(activeEnemy, shortestPathTileHero, this);
		}
	}

	@Override
	public void onOrderFinished() {

		if (roundsMaster.getGameResultMaster().isGameLost()) {
			roundsMaster.reload();
			roundsMaster.getGameResultMaster().resetGameResult();
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
