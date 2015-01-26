package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.players.resolver.DecisionsComponent;
import org.adamsko.cubicforest.players.resolver.MapsResolverClient;

/**
 * Iterator used to iterate through elements during resolving level. Iterator
 * functions are partially implemented by its elements (e.g. component is
 * deciding which element should be next).
 */
public class RoundDecisionsIteratorSolving implements RoundDecisionsIterator {

	DecisionsComponent current;
	// client to which decisions are sent
	MapsResolverClient client;

	RoundDecisionsAggregate roundDecisionsAggregate;

	/**
	 * Iterator initializing first element
	 */
	public RoundDecisionsIteratorSolving(final MapsResolverClient client,
			final RoundDecisionsAggregate roundDecisionsAggregate) {
		this.roundDecisionsAggregate = roundDecisionsAggregate;
		this.current = new RoundDecisionsRoot(client, roundDecisionsAggregate);
	}

	@Override
	public DecisionsComponent next() {
		current = currentItem().nextComponent();
		return currentItem();
	}

	@Override
	public DecisionsComponent first() {
		DecisionsComponent root = currentItem();
		while (!root.getParent().isNull()) {
			root = root.getParent();
		}
		return root;
	}

	@Override
	public boolean isDone() {
		// whole structure is resolved, when root's child has no decisions to
		// make (root is creating first child with starting decisions. all
		// decisions solved: whole structure solved)
		if (!first().getChild().isNull()
				&& first().getChild().getPossibleDecisions().size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLast() {
		return currentItem().getChild().isNull();
	}

	@Override
	public DecisionsComponent currentItem() {
		return current;
	}

	@Override
	public void set(final DecisionsComponent decisionsComponent) {
		this.current = decisionsComponent;
	}
}
