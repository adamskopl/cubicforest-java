package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

public class PhaseEnemies extends PhaseOrderableObjects {

	private final PhaseEnemiesHeroesHelper heroesHelper;

	public PhaseEnemies(final OrderableObjectsContainer enemiesContainer,
			final OrderableObjectsContainer heroesContainer,
			final OrdersMaster ordersMaster) {
		super(enemiesContainer, ordersMaster, "PhaseEnemies");

		heroesHelper = new PhaseEnemiesHeroesHelper(heroesContainer);

	}

	@Override
	public void onTileEvent(final Tile tile, final TileEvent event) {
	}

	@Override
	public void startPhase() {
		moveNextEnemy();
	}

	private void moveNextEnemy() {
		nextObject();
		final WorldObject activeEnemy = activeObject();

		if (activeEnemy == null) {
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
			activeEnemy.restoreMovementPoints();
			ordersMaster.startOrder(activeEnemy, shortestPathTileAdjacentHero,
					this);
		}
	}

	@Override
	public void onOrderFinished() {

		removeDeadObjects();

		if (roundsMaster.getGameResult() == GameResult.GAME_LOST) {
			roundsMaster.reload();
			roundsMaster.resetGameResult();
			return;
		}

		if (isActiveObjectLast()) {
			try {
				phaseIsOver(this);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			return;
		}
		moveNextEnemy();

	}

	@Override
	public void onGuiEvent(final GuiContainer eventGui) {
		// TODO Auto-generated method stub

	}

}
