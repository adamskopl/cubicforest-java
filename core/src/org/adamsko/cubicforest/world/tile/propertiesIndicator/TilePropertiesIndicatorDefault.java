package org.adamsko.cubicforest.world.tile.propertiesIndicator;

public class TilePropertiesIndicatorDefault implements TilePropertiesIndicator {

	private boolean tilePathSearchValid;
	private boolean tileHighlightOccupied;
	private boolean tileEnemiesRangeValid;
	private boolean tileHeroesRangeValid;

	public TilePropertiesIndicatorDefault() {
		tilePathSearchValid = false;
		tileHighlightOccupied = true;
		tileEnemiesRangeValid = true;
		tileHeroesRangeValid = true;
	}

	@Override
	public boolean getTilePathSearchValid() {
		return tilePathSearchValid;
	}

	@Override
	public void setTilePathSearchValid(final boolean tilePathSearchValid) {
		this.tilePathSearchValid = tilePathSearchValid;
	}

	@Override
	public boolean getTileHighlightedAsOccupied() {
		return tileHighlightOccupied;
	}

	@Override
	public void setTileHighlightedAsOccupied(final boolean tileHighlightOccupied) {
		this.tileHighlightOccupied = tileHighlightOccupied;

	}

	@Override
	public boolean getTileEnemiesRangeValid() {
		return tileEnemiesRangeValid;
	}

	@Override
	public void setTileEnemiesRangeValid(final boolean tileEnemiesRangeValid) {
		this.tileEnemiesRangeValid = tileEnemiesRangeValid;
	}

	@Override
	public boolean getTileHeroesRangeValid() {
		return tileHeroesRangeValid;
	}

	@Override
	public void setTileHeroesRangeValid(final boolean tileHeroesRangeValid) {
		this.tileHeroesRangeValid = tileHeroesRangeValid;
	}

}
