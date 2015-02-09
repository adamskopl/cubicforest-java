package org.adamsko.cubicforest.mapsResolver.wmcontainer;

public interface WMContainerMemento {

	WMContainerMementoState getState();

	void setState(WMContainerMementoState state);

	boolean isEqual(WMContainerMemento wmContainerMemento);

}
