package org.adamsko.cubicforest.roundsMaster.memento;

import org.adamsko.cubicforest.mapsResolver.OrderDecision;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GametMemento;

public interface RoundsMasterMapsResolver {

	// order was issued: iterate through next MapsResolver component
	public void resolverIterate();

	public void resolveDecision(OrderDecision orderDecision);

	public GametMemento createMemento();

}
