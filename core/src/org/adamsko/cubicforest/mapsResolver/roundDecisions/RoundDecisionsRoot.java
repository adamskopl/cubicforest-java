package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.NullGameSnapshotMemento;
import org.adamsko.cubicforest.players.resolver.DecisionsComponent;
import org.adamsko.cubicforest.players.resolver.MapsResolverClient;
import org.adamsko.cubicforest.players.resolver.NullDecisionsComponent;

import com.badlogic.gdx.Gdx;

/**
 * Root component: used to start resolving structure. Root is not keeping a
 * snapshot. When resolving starts, it creates first resolving component with a
 * starting snapshot.
 */
public class RoundDecisionsRoot extends RoundDecisions {

	/**
	 * Snapshot from which resolving is started.
	 */
	private GameMemento startingSnapshot;

	public RoundDecisionsRoot(final MapsResolverClient client,
			final RoundDecisionsAggregate roundDecisionsAggregate) {
		super(false);
		startingSnapshot = NullGameSnapshotMemento.instance();

		this.roundDecisionsAggregate = roundDecisionsAggregate;
		this.parent = NullDecisionsComponent.instance();
		this.client = client;
		this.child = NullDecisionsComponent.instance();
	}

	@Override
	public void setSnapshotAfterDecision(
			final GameMemento snapshotAfterPreviousDecision) {
		/*
		 * in the case of the root 'snapshot after decision' means a starting
		 * snapshot
		 */
		this.startingSnapshot = snapshotAfterPreviousDecision;
	}

	@Override
	public DecisionsComponent nextComponent() {
		if (startingSnapshot.isNull()) {
			Gdx.app.error("RoundDecisionsRoot::nextComponent()",
					"startingSnapshot.isNull()");
			return NullDecisionsComponent.instance();
		}
		return createChild(startingSnapshot);
	}

	@Override
	public void makeNextDecision() {
		Gdx.app.debug(
				"end of resolving? RoundDecisionsRoot::makeNextDecision()", "");
	}
}
