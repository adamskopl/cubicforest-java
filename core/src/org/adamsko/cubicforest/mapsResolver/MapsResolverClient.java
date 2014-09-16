package org.adamsko.cubicforest.mapsResolver;

import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GametMemento;

public interface MapsResolverClient {

	void setMemento(GametMemento m);

	GametMemento createMemento();

	void resolveDecision(OrderDecision orderDecision);

}
