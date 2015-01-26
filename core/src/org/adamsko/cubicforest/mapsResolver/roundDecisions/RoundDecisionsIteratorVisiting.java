package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.players.resolver.DecisionsComponent;

/**
 * Iterator used to iterate through ready {@link DecisionsComponent} structure.
 * E.g used to collect all decisions from the structure, to create
 * {@link OrderDecisions}.
 */
public class RoundDecisionsIteratorVisiting implements RoundDecisionsIterator {

	DecisionsComponent current;

	/**
	 * Start with pointing on the passed element
	 */
	public RoundDecisionsIteratorVisiting(
			final DecisionsComponent startingElement) {
		this.current = startingElement;
	}

	@Override
	public DecisionsComponent first() {
		DecisionsComponent root = currentItem();
		while (!root.getParent().isNull()) {
			root = currentItem().getParent();
		}
		return root;
	}

	@Override
	public DecisionsComponent next() {
		current = currentItem().getChild();
		return currentItem();
	}

	@Override
	public boolean isDone() {
		// whole structure is resolved, when all decisions from root are
		// resolved
		return first().isDone();
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
