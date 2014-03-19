package org.adamsko.cubicforest.roundsMaster;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class PhaseEnemies extends PhaseOrderableObjects implements RoundPhase {

	public PhaseEnemies(OrderableObjectsContainer orderableObjectsContainer,
			OrdersMaster ordersMaster) {
		super(orderableObjectsContainer, ordersMaster);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		Gdx.app.debug("phaseEnemies", "onTileEvent");
	}

	@Override
	public void phaseIsOver() throws Exception {
		super.phaseIsOver(this);
	}

	@Override
	public void startPhase() {
		Gdx.app.debug("PhaseEnemies", "startPhase()");

	}

	@Override
	public void onOrderFinished(OrdersMasterResult_e result,
			WorldObject objectWithOrder) {

	}

}
