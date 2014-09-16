package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.mapsResolver.DecisionsComponent;

public class RoundDecisionsIteratorDefault implements RoundDecisionsIterator {

	DecisionsComponent current;

	public RoundDecisionsIteratorDefault(final DecisionsComponent rootComponent) {
		current = rootComponent;
	}

	@Override
	public DecisionsComponent next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DecisionsComponent first() {
		DecisionsComponent root = current;
		while (!root.getParent().isNull()) {
			root = currentItem().getParent();
		}
		return root;
	}

	@Override
	public boolean isDone() {
		// whole structure is resolved, when all decisions from root are
		// resolved
		return first().isDone();
	}

	@Override
	public DecisionsComponent currentItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
