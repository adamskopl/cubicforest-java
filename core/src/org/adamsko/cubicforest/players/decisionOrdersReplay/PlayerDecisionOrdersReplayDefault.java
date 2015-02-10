package org.adamsko.cubicforest.players.decisionOrdersReplay;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.NullOrderDecisionsAggregate;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.NullOrderDecisionsIterator;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecision;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionsAggregate;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionsIterator;
import org.adamsko.cubicforest.players.PlayerBase;
import org.adamsko.cubicforest.players.PlayersController;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathGuideDefault;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PlayerDecisionOrdersReplayDefault extends PlayerBase implements
		PlayerDecisionOrdersReplay {

	/**
	 * Aggregate from which replay will be performed.
	 */
	private OrderDecisionsAggregate aggregateReplayed;
	private OrderDecisionsIterator orderDecisionsIterator;
	private final TilesMaster tilesMaster;

	public PlayerDecisionOrdersReplayDefault(
			final PlayersController playersController,
			final TilesMaster tilesMaster) {
		super(playersController);
		this.aggregateReplayed = NullOrderDecisionsAggregate.instance();
		this.orderDecisionsIterator = NullOrderDecisionsIterator.instance();
		this.tilesMaster = tilesMaster;

		ConditionalLog.addObject(this, "PlayerDecisionOrdersReplayDefault");
		ConditionalLog.setUsage(this, true);
	}

	@Override
	public void initializeRoundsMaster(final RoundsMaster roundsMaster) {
		super.initializeRoundsMaster(roundsMaster);
	}

	@Override
	public void setDecisionOrdersAggregate(
			final OrderDecisionsAggregate replayedAggregate) {
		this.aggregateReplayed = replayedAggregate;
	}

	@Override
	public void startControl() {
		TilePathGuideDefault.setTweenSpeedLow();
		if (this.aggregateReplayed.isNull()) {
			Gdx.app.error("PlayerDecisionOrdersReplayDefault::startControl()",
					"aggregateReplayed.isNull()");
			return;
		}

		roundsMaster.reload();

		this.orderDecisionsIterator = this.aggregateReplayed.createIterator();
		roundsMaster.getCurrentPhase().setActivePlayer(this);

		final OrderDecision orderDecision = this.orderDecisionsIterator
				.currentItem();
		if (ConditionalLog.checkUsage(this)) {
			this.aggregateReplayed.debugPrint();
		}

		issueOrderDecision(orderDecision);
	}

	@Override
	public void makeNextDecision() {
		if (this.orderDecisionsIterator.isDone()) {
			getPlayersController().switchPlayerUser();
			roundsMaster.reload();
		} else {
			final OrderDecision orderDecision = this.orderDecisionsIterator
					.next();
			if (orderDecision.isNull()) {
				Gdx.app.error(
						"PlayerDecisionOrdersReplayDefault::makeNextDecision()",
						"orderDecision.isNull()");
				return;
			}
			issueOrderDecision(orderDecision);
		}
	}

	private void issueOrderDecision(final OrderDecision orderDecision) {
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
	public void onVictoryConditionsMet(final boolean prizesCollected) {
		roundsMaster.reload();
		getPlayersController().switchPlayerUser();
	}

}
