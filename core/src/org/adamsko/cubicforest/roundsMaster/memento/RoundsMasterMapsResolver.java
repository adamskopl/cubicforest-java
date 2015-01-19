package org.adamsko.cubicforest.roundsMaster.memento;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.players.resolver.MapsResolverClient;
import org.adamsko.cubicforest.players.resolver.OrderDecisionDefault;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;

public interface RoundsMasterMapsResolver {

	// order was issued: iterate through next MapsResolver component
	public void resolveDecision(OrderDecisionDefault orderDecision);

	public GameMemento createMemento();

	void initializeResolveIterator(final MapsResolverClient client,
			final PhaseHeroes resolvedPhase);

	/**
	 * Victory conditions are met: resolver should remember victorious orders
	 * sequence.
	 */
	void victoryConditionsMet();

}
