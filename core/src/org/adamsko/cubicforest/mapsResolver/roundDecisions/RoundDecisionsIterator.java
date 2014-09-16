package org.adamsko.cubicforest.mapsResolver.roundDecisions;

import org.adamsko.cubicforest.mapsResolver.DecisionsComponent;

public interface RoundDecisionsIterator {

	DecisionsComponent first();

	DecisionsComponent next();

	boolean isDone();

	DecisionsComponent currentItem();

}
