package org.adamsko.cubicforest.players.resolver;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroesOrdersMaster;

public interface MapsResolverClient {

	GameMemento createMemento();

	void setMemento(GameMemento memento);

	void resolveDecision(OrderDecisionDefault orderDecision);

	void initializeResolveIterator(final PhaseHeroes resolvedPhase);

	/**
	 * {@link PhaseHeroesOrdersMaster#getCurrentPossbileDecisions()}
	 */
	List<OrderDecisionDefault> getCurrentPossbileDecisions();

}
