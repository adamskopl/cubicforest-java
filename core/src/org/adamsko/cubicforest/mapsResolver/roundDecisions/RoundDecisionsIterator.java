package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.players.resolver.DecisionsComponent;

public interface RoundDecisionsIterator {

	DecisionsComponent first();

	DecisionsComponent next();

	boolean isDone();

	boolean isLast();

	DecisionsComponent currentItem();

	/**
	 * Set iterator to point on the passed element
	 */
	void set(DecisionsComponent decisionsComponent);

}
