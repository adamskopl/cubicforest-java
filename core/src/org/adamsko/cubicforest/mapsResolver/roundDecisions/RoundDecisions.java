package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.NullGameSnapshotMemento;
import org.adamsko.cubicforest.players.resolver.DecisionsComponent;
import org.adamsko.cubicforest.players.resolver.MapsResolverClient;
import org.adamsko.cubicforest.players.resolver.NullDecisionsComponent;
import org.adamsko.cubicforest.players.resolver.NullOrderDecision;
import org.adamsko.cubicforest.players.resolver.OrderDecisionDefault;
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

	protected RoundDecisionsAggregate roundDecisionsAggregate;

	/**
	 * Game snapshot for which resolving component is responsible
	 */
	private final GameMemento snapshot;

	// // snapshot from which component is starting. his snapshot is not
	// changed.
	// final GameMemento snapshot;

	// game snapshot changed after next making available decision. used to
	// create eventual new components, which will resolve this snapshot
	GameMemento snapshotAfterPreviousDecision;

	// what hero can do with snapshotAfterChoice memento?
	private final List<OrderDecisionDefault> possibleDecisions;

	// Possible victorious decision.
	private OrderDecisionDefault latestDecision;

	protected DecisionsComponent parent;

	protected MapsResolverClient client;

	/**
	 * One child is resolved for one {@link RoundDecisions}
	 */
	protected DecisionsComponent child;

	// null constructor
	public RoundDecisions(final boolean nullConstructor) {
		this.possibleDecisions = new ArrayList<OrderDecisionDefault>();
		this.parent = null;
		this.snapshot = NullGameSnapshotMemento.instance();
		roundDecisionsAggregate = null;
	}

	public RoundDecisions(final MapsResolverClient mapsResolverClient,
			final RoundDecisionsAggregate roundDecisionsAggregate,
			final DecisionsComponent parent, final GameMemento startingSnapshot) {

		this.roundDecisionsAggregate = roundDecisionsAggregate;
		this.parent = parent;
		this.snapshot = startingSnapshot;

		// if the component is created, it means that starting snapshot was not
		// resolved
		roundDecisionsAggregate.addResolvedState(startingSnapshot);

		this.client = mapsResolverClient;
		possibleDecisions = client.getCurrentPossbileDecisions();
		latestDecision = NullOrderDecision.instance();

		// child will be eventually added later (for snapshotAfterDecision)
		child = NullDecisionsComponent.instance();

	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public DecisionsComponent nextComponent() {

		// check if memento was resolved
		if (roundDecisionsAggregate
				.isMementoResolved(snapshotAfterPreviousDecision)) {
			// continue resolving current element
			return this;
		}

		return createChild(snapshotAfterPreviousDecision);

	}

	@Override
	public void makeNextDecision() {
		if (getPossibleDecisions().size() == 0
				|| getHeight() >= roundDecisionsAggregate.getMaxDepth()) {
			// next decision should be made by a parent
			parent.makeNextDecision();
			return;
		}

		// return to the snapshot for which this component is responsible
		client.setMemento(snapshot);

		/*
		 * Possible victorious decision. When victory conditions will be met,
		 * this decision will be remembered.
		 */
		latestDecision = possibleDecisions.remove(0);

		client.resolveDecision(latestDecision);
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
	}

	@Override
	public DecisionsComponent getChild() {
		return child;
	}

	@Override
	public DecisionsComponent getParent() {
		return parent;
	}

	@Override
	public int getHeight() {
		int height = 0;
		DecisionsComponent root = this;
		while (!root.getParent().isNull()) {
			root = root.getParent();
			height++;
		}
		return height;
	}

	@Override
	public boolean isDone() {
		// component is completely resolved, when all possible decisions are
		// taken into account
		if (possibleDecisions.size() == 0) {
			return true;
		}

		return false;
	}

	@Override
	public void setSnapshotAfterDecision(
			final GameMemento snapshotAfterPreviousDecision) {
		this.snapshotAfterPreviousDecision = snapshotAfterPreviousDecision;
	};

	List<OrderDecisionDefault> getPossibleDecisions() {
		return possibleDecisions;
	}

	protected DecisionsComponent createChild(final GameMemento childSnapshot) {
		/*
		 * game snapshot after previous decision was not resolved and should be
		 * resolved by the next component
		 */
		final DecisionsComponent newChild = new RoundDecisions(client,
				roundDecisionsAggregate, this, childSnapshot);
		add(newChild);

		return newChild;
	}

	@Override
	public OrderDecisionDefault getLatestDecision() {
		return latestDecision;
	}

}
