package org.adamsko.cubicforest.roundsMaster.memento;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameSnapshotMementoDefault;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameState;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.players.resolver.MapsResolver;
import org.adamsko.cubicforest.players.resolver.MapsResolverClient;
import org.adamsko.cubicforest.players.resolver.OrderDecisionDefault;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;

public class RoundsMasterMapsResolverDefault implements
		RoundsMasterMapsResolver {

	// used for creating GameSnapshotMemento
	private final WorldObjectsMastersContainer worldObjectsMastersContainer;
	PhaseHeroes resolvedPhase;

	private final MapsResolver mapsResolver;

	private RoundDecisionsIterator decisionsIterator;

	public RoundsMasterMapsResolverDefault(final MapsResolver mapsResolver,
			final WorldObjectsMastersContainer worldObjectsMastersContainer) {

		this.worldObjectsMastersContainer = worldObjectsMastersContainer;

		this.mapsResolver = mapsResolver;

		this.decisionsIterator = null;
		this.resolvedPhase = null;
	}

	@Override
	public void resolveDecision(final OrderDecisionDefault orderDecision) {
		resolvedPhase.resolveDecision(orderDecision);
	}

	@Override
	public GameMemento createMemento() {
		final WMContainerMemento wmContainerMemento = worldObjectsMastersContainer
				.createMemento();
		final int currentHeroIndex = resolvedPhase.currentObjectIndex();

		final GameState snapshot = new GameState(wmContainerMemento,
				currentHeroIndex);

		final GameMemento memento = new GameSnapshotMementoDefault();
		memento.setState(snapshot);

		return memento;
	}

	@Override
	public void initializeResolveIterator(final MapsResolverClient client,
			final PhaseHeroes resolvedPhase) {
		this.resolvedPhase = resolvedPhase;
		decisionsIterator = mapsResolver.createRoundDecisionsIterator(client);
		this.resolvedPhase.setRoundDecisionsIterator(decisionsIterator,
				mapsResolver);

	}

	@Override
	public void victoryConditionsMet() {
		mapsResolver.victoryConditionsMet();
	}

}
