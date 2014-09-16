package org.adamsko.cubicforest.mapsResolver;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GametMemento;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisions;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsAggregate;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIteratorDefault;

public class MapsResolver implements RoundDecisionsAggregate {

	DecisionsComponent root;

	public MapsResolver(final GametMemento startingMemento) {
		// create root decisions component with null parent
		root = new RoundDecisions(NullDecisionsComponent.instance(),
				startingMemento);
	}

	@Override
	public RoundDecisionsIterator createIterator() {
		return new RoundDecisionsIteratorDefault(root);
	}

}
