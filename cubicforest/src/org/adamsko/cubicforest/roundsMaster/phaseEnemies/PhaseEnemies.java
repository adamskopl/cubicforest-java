package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.roundsMaster.PhaseOrderableObjects;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult_e;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class PhaseEnemies extends PhaseOrderableObjects implements RoundPhase {

	private PhaseEnemiesHeroesHelper heroesHelper;

	public PhaseEnemies(OrderableObjectsContainer enemiesContainer,
			OrderableObjectsContainer heroesContainer, OrdersMaster ordersMaster) {
		super(enemiesContainer, ordersMaster, "PhaseEnemies");

		heroesHelper = new PhaseEnemiesHeroesHelper(heroesContainer);

	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		// Gdx.app.debug("phaseEnemies", "onTileEvent");
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
		TilePath shortestPathTileAdjacentHero = heroesHelper
				.searchPathShortestHero(activeEnemy);

		if (shortestPathTileAdjacentHero == null
				|| shortestPathTileAdjacentHero.length() == 0) {

			onOrderFinished(OrdersMasterResult_e.ORDER_PATH_NOTFOUND,
					activeEnemy);
		} else {
			// shorten path to enemy's speed
			shortestPathTileAdjacentHero.shortenPath(activeEnemy.getSpeed());
//			Gdx.app.debug(getName(), activeEnemy.getName() + " startOrder");
			ordersMaster.startOrder(activeEnemy, shortestPathTileAdjacentHero,
					this);
		}
	}

	@Override
	public void onOrderFinished(OrdersMasterResult_e result,
			WorldObject objectWithOrder) {

		if (activeObject() != objectWithOrder) {
			Gdx.app.error(getName(), "activeObject() != objectWithOrder");
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
