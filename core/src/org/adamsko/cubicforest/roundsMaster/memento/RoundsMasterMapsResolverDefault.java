package org.adamsko.cubicforest.roundsMaster.memento;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.MapsResolver;
import org.adamsko.cubicforest.mapsResolver.OrderDecision;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GametMemento;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameSnapshotMementoDefault;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameState;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsAggregate;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;

public class RoundsMasterMapsResolverDefault implements
		RoundsMasterMapsResolver {

	// used for creating GameSnapshotMemento
	private final WorldObjectsMastersContainer worldObjectsMastersContainer;
	PhaseHeroes resolvedPhase;

	private final RoundDecisionsAggregate roundDecisionsAggregate;
	private final RoundDecisionsIterator decisionsIterator;

	public RoundsMasterMapsResolverDefault(final PhaseHeroes resolvedPhase,
			final WorldObjectsMastersContainer worldObjectsMastersContainer) {
		this.resolvedPhase = resolvedPhase;
		this.worldObjectsMastersContainer = worldObjectsMastersContainer;

		roundDecisionsAggregate = new MapsResolver(createMemento());
		decisionsIterator = roundDecisionsAggregate.createIterator();

	}

	@Override
	public void resolveDecision(final OrderDecision orderDecision) {

	}

	@Override
	public void resolverIterate() {
		// TODO Auto-generated method stub

	}

	@Override
	public GametMemento createMemento() {
		final WMContainerMemento wmContainerMemento = worldObjectsMastersContainer
				.createMemento();
		final int currentHeroIndex = resolvedPhase.currentObjectIndex();
		final List<OrderDecision> possibleChoices = resolvedPhase
				.getCurrentPossbileDecisions();

		final GameState snapshot = new GameState(
				wmContainerMemento, currentHeroIndex, possibleChoices);

		final GametMemento memento = new GameSnapshotMementoDefault();
		memento.setState(snapshot);

		return memento;
	}

}
