package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterPathResult;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.Gdx;

public class PhaseEnemies extends PhaseOrderableObjects {

	private PhaseEnemiesHeroesHelper heroesHelper;

	public PhaseEnemies(OrderableObjectsContainer enemiesContainer,
			OrderableObjectsContainer heroesContainer, OrdersMaster ordersMaster) {
		super(enemiesContainer, ordersMaster, "PhaseEnemies");

		heroesHelper = new PhaseEnemiesHeroesHelper(heroesContainer);

	}

	@Override
	public void onTileEvent(Tile tile, TileEvent event) {
	}

	@Override
	public void phaseIsOver() throws Exception {
		super.phaseIsOver(this);
	}

	@Override
	public void startPhase() {
		moveNextEnemy();
	}

	private void moveNextEnemy() {
		nextObject();
		WorldObject activeEnemy = activeObject();

		if (activeEnemy == null)
			return;

		TilePath shortestPathTileAdjacentHero = heroesHelper
				.searchPathShortestHero(activeEnemy);

		if (shortestPathTileAdjacentHero == null
				|| shortestPathTileAdjacentHero.length() == 0) {

			onOrderFinished(new OrdersMasterResult(
					OrdersMasterPathResult.ORDER_PATH_NOTFOUND), activeEnemy);

		} else {
			// shorten path to enemy's speed
			shortestPathTileAdjacentHero.shortenPath(activeEnemy.getSpeed());
			activeEnemy.restoreMovementPoints();
			ordersMaster.startOrder(activeEnemy, shortestPathTileAdjacentHero,
					this);
		}
	}

	@Override
	public void onOrderFinished(OrdersMasterResult result,
			WorldObject objectWithOrder) {

		if (roundsMaster.getGameResult() == GameResult.GAME_LOST) {
			if (roundsMaster.isReloadAllowed()) {
				roundsMaster.reload();
				roundsMaster.resetGameResult();
				return;
			} else
				Gdx.app.error("onOrderFinished()",
						"roundsMaster.isReloadAllowed()==false");
			
			roundsMaster.resetGameResult();
		}

		if (isActiveObjectLast()) {
			try {
				phaseIsOver();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		moveNextEnemy();

	}

	@Override
	public void onGuiEvent(GuiContainer eventGui) {
		// TODO Auto-generated method stub

	}

}
