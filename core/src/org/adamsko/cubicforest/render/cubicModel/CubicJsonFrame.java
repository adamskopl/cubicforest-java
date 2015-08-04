package org.adamsko.cubicforest.render.cubicModel;

import java.util.List;

public class CubicJsonFrame {

	/**
	 * Frame number.
	 */
	private int number;
	/**
	 * Frame's cube groups.
	 */
	private List<CubicJsonGroup> groups;

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public List<CubicJsonGroup> getGroups() {
		return groups;
	}

	public void setGroups(final List<CubicJsonGroup> groups) {
		this.groups = groups;
	}

}
