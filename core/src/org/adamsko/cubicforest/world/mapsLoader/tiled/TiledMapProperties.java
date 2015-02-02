package org.adamsko.cubicforest.world.mapsLoader.tiled;

/**
 * Properties loaded from Tiled json file.<br>
 * Tiled: Map->Map Properties
 */
public class TiledMapProperties {

	private int startingCubes;
	private boolean toolOrange;
	private boolean toolExit;
	private boolean toolRed;
	private boolean toolTrap;
	private boolean toolTurret;

	public int getStartingCubes() {
		return startingCubes;
	}

	public void setStartingCubes(final String startingCubes) {
		this.startingCubes = Integer.parseInt(startingCubes);
	}

	public boolean getToolOrange() {
		return toolOrange;
	}

	public void setToolOrange(final String toolOrange) {
		this.toolOrange = Boolean.parseBoolean(toolOrange);
	}

	public boolean getToolExit() {
		return toolExit;
	}

	public void setToolExit(final String toolExit) {
		this.toolExit = Boolean.parseBoolean(toolExit);
	}

	public boolean getToolRed() {
		return toolRed;
	}

	public void setToolRed(final String toolRed) {
		this.toolRed = Boolean.parseBoolean(toolRed);
	}

	public boolean getToolTrap() {
		return toolTrap;
	}

	public void setToolTrap(final String toolTrap) {
		this.toolTrap = Boolean.parseBoolean(toolTrap);
	}

	public boolean getToolTurret() {
		return toolTurret;
	}

	public void setToolTurret(final String toolTurret) {
		this.toolTurret = Boolean.parseBoolean(toolTurret);
	}

}
