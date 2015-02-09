package org.adamsko.cubicforest.mapsResolver.wmcontainer;

public class WMContainerMementoDefault implements WMContainerMemento {

	// memento state
	WMContainerMementoState state;

	@Override
	public WMContainerMementoState getState() {
		return state;
	}

	@Override
	public void setState(final WMContainerMementoState state) {
		this.state = state;
	}

	@Override
	public boolean isEqual(final WMContainerMemento checkedMemento) {
		return checkedMemento.getState().isEqual(getState());
	}

}
