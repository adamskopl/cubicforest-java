package org.adamsko.cubicforest.players.resolver;

import java.util.List;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;
import org.adamsko.cubicforest.players.PlayerBase;
import org.adamsko.cubicforest.players.PlayersController;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathGuideDefault;

import com.badlogic.gdx.math.Vector2;

public class PlayerMapsResolver extends PlayerBase implements
		MapsResolverClient {

	private PhaseHeroes resolvedPhase;
	private RoundDecisionsIterator roundDecisionsIterator;
	private final MapsResolver mapsResolver;
	private final TilesMaster tilesMaster;

	public PlayerMapsResolver(final PlayersController playersController,
			final MapsResolver mapsResolver, final TilesMaster tilesMaster) {
		super(playersController);
		this.mapsResolver = mapsResolver;
		this.tilesMaster = tilesMaster;

		CLog.addObject(this, "PlayerMapsResolver");
		CLog.setUsage(this, false);
	}

	@Override
	public void initializeRoundsMaster(final RoundsMaster roundsMaster) {
		super.initializeRoundsMaster(roundsMaster);
		initializeResolveIterator(roundsMaster.getPhaseHeroes());
	}

	@Override
	public void startControl() {
		TilePathGuideDefault.setTweenSpeedHigh();
		resolvedPhase.setActivePlayer(this);
		mapsResolver.startNewResolve();
		makeNextDecision();
	}

	@Override
	public void onVictoryConditionsMet(final boolean prizesCollected) {
		mapsResolver.victoryConditionsMet(prizesCollected);
	}

	@Override
	public void makeNextDecision() {

		final GameMemento memento = createMemento();
		// inform current component about the results of the last decision
		roundDecisionsIterator.currentItem().setSnapshotAfterDecision(memento);

		if (roundDecisionsIterator.isDone()) {
			// resolver is done
			CLog.debug(this, "iterator is done");
			getPlayersController().switchPlayerUser();
		} else {
			roundDecisionsIterator.next().makeNextDecision(
					roundDecisionsIterator);
		}
	}

	@Override
	public GameMemento createMemento() {
		return roundsMaster.createMemento();
	}

	@Override
	public void setMemento(final GameMemento memento) {
		roundsMaster.setMemento(memento);
	}

	@Override
	public void resolveDecision(final OrderDecisionDefault orderDecision) {
		final Vector2 decisionPos = orderDecision.getChosenTilePos();
		final Tile chosenTile = tilesMaster.getTilesContainer().getTileOnPos(
				decisionPos);

		roundsMaster.getCurrentPhase().getPlayerActionsHandler()
				.onTileChoice(chosenTile);
		if (orderDecision.heroToolChosen()) {
			roundsMaster.getCurrentPhase().getPlayerActionsHandler()
					.onHeroToolChoice(orderDecision.getChosenHeroTool());
		}

		roundsMaster.getCurrentPhase().getPlayerActionsHandler().onConfirm();
	}

	@Override
	public void initializeResolveIterator(final PhaseHeroes resolvedPhase) {
		this.resolvedPhase = resolvedPhase;
		this.roundDecisionsIterator = mapsResolver
				.createRoundDecisionsIterator(this);
	}

	@Override
	public List<OrderDecisionDefault> getCurrentPossbileDecisions() {
		return resolvedPhase.getOrdersMaster().getCurrentPossbileDecisions();
	}
}
