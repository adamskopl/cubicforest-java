package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.NullGameSnapshotMemento;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.players.resolver.DecisionsComponent;
import org.adamsko.cubicforest.players.resolver.MapsResolverClient;
import org.adamsko.cubicforest.players.resolver.NullDecisionsComponent;
import org.adamsko.cubicforest.players.resolver.NullOrderDecision;
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

	public static int tempIdCounter = 0;
	public int tempId;

	// null constructor
	public RoundDecisions(final boolean nullConstructor) {
		this.possibleDecisions = new ArrayList<OrderDecisionDefault>();
		this.parent = null;
		this.snapshot = NullGameSnapshotMemento.instance();
		this.snapshotAfterPreviousDecision = NullGameSnapshotMemento.instance();
		roundDecisionsAggregate = null;
	}

	public RoundDecisions(final MapsResolverClient mapsResolverClient,
			final RoundDecisionsAggregate roundDecisionsAggregate,
			final DecisionsComponent parent, final GameMemento startingSnapshot) {

		this.roundDecisionsAggregate = roundDecisionsAggregate;
		this.parent = parent;
		this.snapshot = startingSnapshot;
		this.snapshotAfterPreviousDecision = NullGameSnapshotMemento.instance();

		// if the component is created, it means that starting snapshot was not
		// resolved
		roundDecisionsAggregate.addResolvedState(startingSnapshot);

		this.client = mapsResolverClient;
		possibleDecisions = client.getCurrentPossbileDecisions();
		latestDecision = NullOrderDecision.instance();

		// child will be eventually added later (for snapshotAfterDecision)
		child = NullDecisionsComponent.instance();

		tempId = tempIdCounter;
		tempIdCounter++;

		String decisionsString = new String();
		for (final OrderDecisionDefault decision : possibleDecisions) {
			final String dString = new String(decision.getChosenTilePos()
					.toString());
			decisionsString = decisionsString.concat(dString + ",");
		}

		ConditionalLog.addObject(this,
				"RoundDecisions " + Integer.toString(getHeight()) + ","
						+ Integer.toString(startingSnapshot.getTempId()));
		ConditionalLog.setUsage(this, false);
		ConditionalLog.debug(this, toString());

	}

	@Override
	public String toString() {
		String string = new String();
		for (final OrderDecisionDefault orderDecisionDefault : possibleDecisions) {
			string = string.concat(orderDecisionDefault.toString() + " ");
		}
		return string;
	}

	@Override
	public int getTempId() {
		return tempId;
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
			ConditionalLog.debug(
					this,
					"solved snapshot: "
							+ Integer.toString(snapshotAfterPreviousDecision
									.getTempId()));
			// continue resolving current element
			return this;
		}
		return createChild(snapshotAfterPreviousDecision);
	}

	@Override
	public boolean isDone() {
		if (possibleDecisions.size() == 0
				&& roundDecisionsAggregate
						.isMementoResolved(snapshotAfterPreviousDecision)) {
			// if there are no decisions to make, and snapshot from previous
			// decision is solved
			if (child.isNull() || child.isDone()) {
				// if there is a child, check if it's done. situation: when
				// there is a tree root->child0->child1 and child0 and child1
				// are done.
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeNextDecision(
			final RoundDecisionsIterator roundDecisionsIterator) {
		if (getPossibleDecisions().size() == 0
				|| getHeight() >= roundDecisionsAggregate.getMaxDepth()) {
			// Detach from parent (this component will not be part of the
			// soulution). Right now parent should have this component as a
			// child.
			if (!parent.getChild().isNull()) {
				parent.remove(this);
			}
			roundDecisionsIterator.set(parent);
			// Next decision should be made by a parent.
			parent.makeNextDecision(roundDecisionsIterator);
			return;
		}

		// return to the snapshot for which this component is responsible
		client.setMemento(snapshot);

		/*
		 * Possible victorious decision. When victory conditions will be met,
		 * this decision will be remembered.
		 */
		latestDecision = possibleDecisions.remove(0);

		ConditionalLog.debug(this, "issuing " + latestDecision.toString()
				+ " remained " + Integer.toString(possibleDecisions.size()));

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
		if (child != decisionsComponent) {
			Gdx.app.error("RoundDecisions::remove()",
					"child != decisionsComponent");
			return;
		}
		this.child = NullDecisionsComponent.instance();
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
	public void setSnapshotAfterDecision(
			final GameMemento snapshotAfterPreviousDecision) {

		if (parent.getChild() != this) {
			parent.setSnapshotAfterDecision(snapshotAfterPreviousDecision);
			return;
		}

		ConditionalLog.debug(
				this,
				"setSnapshotAfterDecision "
						+ Integer.toString(snapshotAfterPreviousDecision
								.getTempId()));
		this.snapshotAfterPreviousDecision = snapshotAfterPreviousDecision;
	}

	@Override
	public List<OrderDecisionDefault> getPossibleDecisions() {
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
