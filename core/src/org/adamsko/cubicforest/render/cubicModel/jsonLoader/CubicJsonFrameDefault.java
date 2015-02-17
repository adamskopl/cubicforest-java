package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

public class CubicJsonFrameDefault implements CubicJsonFrame {

	/**
	 * Frame number.
	 */
	private int number;
	/**
	 * Frame's cube groups.
	 */
	private List<CubicJsonGroup> groups;

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void setNumber(final int number) {
		this.number = number;
	}

	@Override
	public List<CubicJsonGroup> getGroups() {
		return groups;
	}

	@Override
	public void setGroups(final List<CubicJsonGroup> groups) {
		this.groups = groups;
	}

}
