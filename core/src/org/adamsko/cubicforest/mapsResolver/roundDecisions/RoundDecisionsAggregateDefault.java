package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.players.resolver.DecisionsComponent;
import org.adamsko.cubicforest.players.resolver.NullDecisionsComponent;

import com.badlogic.gdx.Gdx;

public class RoundDecisionsAggregateDefault implements RoundDecisionsAggregate {

	DecisionsComponent root;

	private final List<GameMemento> resolvedStates;

	private final int maxDepth;

	RoundDecisionsIterator roundDecisionsIterator;
	// iterator iterating through elements to find victorious
	RoundDecisionsIterator victoriousIterator;

	public RoundDecisionsAggregateDefault(final int maxDepth) {
		resolvedStates = new ArrayList<GameMemento>();
		// solutions = new ArrayList<VictoriousDecisions>();
		this.maxDepth = maxDepth;

		victoriousIterator = new RoundDecisionsIteratorVisiting(
				NullDecisionsComponent.instance());
	}

	@Override
	public void addResolvedState(final GameMemento gameMemento) {
		if (isMementoResolved(gameMemento)) {
			Gdx.app.error("MapsResolver::addResolvedState()",
					"memento is already resolved");
			return;
		}
		resolvedStates.add(gameMemento);
	}

	@Override
	public boolean isMementoResolved(final GameMemento checkedMemento) {
		if (checkedMemento.isNull()) {
			Gdx.app.error(
					"RoundDecisionsAggregateDefault::isMementoResolved()",
					"checkedMemento.isNull())");
			return true;
		}
		for (final GameMemento resolvedMemento : resolvedStates) {
			if (checkedMemento.isEqual(resolvedMemento)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getMaxDepth() {
		return maxDepth;
	}

}
