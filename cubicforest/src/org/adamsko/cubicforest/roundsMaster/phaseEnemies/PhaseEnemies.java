package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.OrdersMasterResultResolver;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation_e;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterPathResult_e;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class PhaseEnemies extends PhaseOrderableObjects {

	private PhaseEnemiesHeroesHelper heroesHelper;

	public PhaseEnemies(OrderableObjectsContainer enemiesContainer,
			OrderableObjectsContainer heroesContainer,
			OrdersMaster ordersMaster,
			OrdersMasterResultResolver ordersMasterResultResolver) {
		super(enemiesContainer, ordersMaster, ordersMasterResultResolver,
				"PhaseEnemies");

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
		
		if(activeEnemy == null)return;
		
		TilePath shortestPathTileAdjacentHero = heroesHelper
				.searchPathShortestHero(activeEnemy);

		if (shortestPathTileAdjacentHero == null
				|| shortestPathTileAdjacentHero.length() == 0) {

			onOrderFinished(new OrdersMasterResult(
					OrdersMasterPathResult_e.ORDER_PATH_NOTFOUND), activeEnemy);

		} else {
			// shorten path to enemy's speed
			shortestPathTileAdjacentHero.shortenPath(activeEnemy.getSpeed());
			// Gdx.app.debug(getName(), activeEnemy.getName() + " startOrder");
			ordersMaster.startOrder(activeEnemy, shortestPathTileAdjacentHero,
					this);
		}
	}

	@Override
	public void onOrderFinished(OrdersMasterResult result,
			WorldObject objectWithOrder) {

		if (result.getInteractionResult().getObjectOperation() == ObjectOperation_e.OBJECT_REMOVE) {
			Gdx.app.debug("PhaseEnemies", "REMOVE " + objectWithOrder.getName());
		}
		
		if (activeObject() != objectWithOrder) {
			Gdx.app.error(getName(), "activeObject() != objectWithOrder");
		}
		
		ordersMasterResultResolver.resolve(result, objectWithOrder, this);


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
