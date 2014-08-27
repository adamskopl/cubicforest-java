package org.adamsko.cubicforest.world.tile.propertiesIndicator;

public class TilePropertiesIndicatorDefault implements TilePropertiesIndicator {

	private boolean tilePathSearchValid;
	private final boolean tileHighlightOccupied;

	public TilePropertiesIndicatorDefault() {
		tilePathSearchValid = false;
		tileHighlightOccupied = true;
	}

	@Override
	public boolean isTilePathSearchValid() {
		return tilePathSearchValid;
	}

	@Override
	public void setTilePathSearchValid(final boolean tilePathSearchValid) {
		this.tilePathSearchValid = tilePathSearchValid;
	}

	@Override
	public boolean isTileHighlightedAsOccupied() {
		return tileHighlightOccupied;
	}

}
