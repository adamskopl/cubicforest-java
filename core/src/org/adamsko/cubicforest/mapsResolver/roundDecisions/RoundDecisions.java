package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.DecisionsComponent;
import org.adamsko.cubicforest.mapsResolver.NullDecisionsComponent;
import org.adamsko.cubicforest.mapsResolver.OrderDecision;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GametMemento;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.NullGameSnapshotMemento;
import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.RoundsMasterDefault;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.Gdx;

/**
 * Component that is resolving available decisions for {@link WorldObject}
 * object. Creates decisions and issue them to be ordered.
 * 
 * @author adamsko
 * 
 */
public class RoundDecisions implements DecisionsComponent {

	// game snapshot right before performing tile choice
	GametMemento previousSnapshot;

	// game snapshot after performing tile choice
	GametMemento snapshotAfterDecision;

	// what hero can do with snapshotAfterChoice memento?
	private final List<OrderDecision> possibleDecisions;

	private final DecisionsComponent parent;

	/**
	 * One child is resolved for one {@link RoundDecisions}
	 */
	private DecisionsComponent child;

	// null constructor
	public RoundDecisions(final boolean nullConstructor) {
		this.possibleDecisions = null;
		this.parent = null;
	}

	public RoundDecisions(final DecisionsComponent parent,
			final GametMemento startingSnapshot) {

		this.parent = parent;
		this.previousSnapshot = startingSnapshot;
		if (previousSnapshot.isNull()) {
			Gdx.app.error("RoundDecisions()", "previousSnapshot.isNull()");
		}

		this.snapshotAfterDecision = NullGameSnapshotMemento.instance();

		child = NullDecisionsComponent.instance();

		final RoundsMaster roundsMaster = new RoundsMasterDefault(
				previousSnapshot);
		final PhaseHeroes phaseHeroes = roundsMaster.getPhaseHeroes();
		possibleDecisions = phaseHeroes.getCurrentPossbileDecisions();
	}

	public RoundDecisions(final DecisionsComponent parent) {
		this(parent, parent.getSnapshotAfterChoice());
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void makeDecision() {

		// take next decision, send it to the client
		if (possibleDecisions.size() != 0) {
			// error: if makeDecision() is invoked, it means there should be
			// possible decisions available
			Gdx.app.error("RoundDecisions::makeDecision()",
					"possibleDecisions.size() != 0");
		}
		final OrderDecision nextDecision = possibleDecisions.remove(0);

	}

	@Override
	public void add(final DecisionsComponent decisionsComponent) {
		if (decisionsComponent.isNull()) {
			Gdx.app.error("RoundDecisions::add()",
					"decisionsComponent.isNull()");
		}
		this.child = decisionsComponent;
	}

	@Override
	public void remove(final DecisionsComponent decisionsComponent) {
		// TODO Auto-generated method stub

	}

	@Override
	public DecisionsComponent nextComponent() {
		if (snapshotAfterDecision.isNull()) {
			Gdx.app.error("RoundDecisions::nextComponent()",
					"(snapshotAfterChoice.isNull()");
			return NullDecisionsComponent.instance();
		}

		if (snapshotAfterDecision.getState().getGameResult() == GameResult.GAME_PLAY) {
			final DecisionsComponent child = new RoundDecisions(this);
			add(child);
			return child;
		}

		if (isDone()) {
			// no choices available, return to parent
			return parent;
		}

		// children is handled, choices available. continue work by creating a
		// new child
		return this;
	}

	@Override
	public GametMemento getSnapshotAfterChoice() {
		return snapshotAfterDecision;
	}

	@Override
	public DecisionsComponent getChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DecisionsComponent getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDone() {
		// component is completely resolved, when all possible decisions are
		// taken into account
		return possibleDecisions.size() == 0;
	}

}
