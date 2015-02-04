package org.adamsko.cubicforest.mapsResolver.wmcontainer;

/**
 * WorldObjectsMaster memento.
 */
public interface WOMMemento {

	WOMMementoState getState();

	void setState(WOMMementoState state);

	boolean isEqual(WOMMemento womMemento);

}
