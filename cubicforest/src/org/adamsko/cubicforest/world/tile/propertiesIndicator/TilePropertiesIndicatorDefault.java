package org.adamsko.cubicforest.world.tile.propertiesIndicator;


public class TilePropertiesIndicatorDefault implements TilePropertiesIndicator {

	private boolean tilePathSearchValid;

	public TilePropertiesIndicatorDefault() {
		tilePathSearchValid = false;
	}

	@Override
	public boolean getTilePathSearchValid() {
		return tilePathSearchValid;
	}

	@Override
	public void setTilePathSearchValid(final boolean tilePathSearchValid) {
		this.tilePathSearchValid = tilePathSearchValid;
	}

}
