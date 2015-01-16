package org.adamsko.cubicforest.mapsResolver.wmcontainer;

public class WOMMementoDefault implements WOMMemento {

	private WOMMementoState state;

	@Override
	public WOMMementoState getState() {
		return state;
	}

	@Override
	public void setState(final WOMMementoState state) {
		this.state = state;
	}

	@Override
	public boolean isEqual(final WOMMemento womMemento) {
		return womMemento.getState().isEqual(getState());
	}

}
