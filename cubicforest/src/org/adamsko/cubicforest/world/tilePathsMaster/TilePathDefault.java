package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.Gdx;

public class TilePathDefault implements TilePath {

	/**
	 * List of tiles which wanderer has to go through.
	 */
	private final List<Tile> tilesList;

	public TilePathDefault() {
		tilesList = new ArrayList<Tile>();
	}

	/**
	 * Create path with a single tile.
	 * 
	 * @param tile
	 */
	public TilePathDefault(final Tile tile) {
		this();
		pushTile(tile);
	}

	@Override
	public void pushTile(final Tile newTile) {
		tilesList.add(newTile);
	}

	@Override
	public void addTileFront(final Tile newTile) {
		tilesList.add(0, newTile);
	}

	@Override
	public boolean isEmpty() {
		return tilesList.isEmpty();
	}

	@Override
	public Tile removeFrontTile() {
		try {
			return tilesList.remove(0);
		} catch (final IndexOutOfBoundsException ex) {
			Gdx.app.error("TilePath", ex.toString());
			return null;
		}
	}

	@Override
	public Tile getFrontTile() {
		try {
			return tilesList.get(0);
		} catch (final IndexOutOfBoundsException ex) {
			Gdx.app.error("TilePath", ex.toString());
			return null;
		}
	}

	@Override
	public void shortenPath(final int shortLimit) {
		if (length() <= shortLimit) {
			return;
		}
		for (int i = length() - 1; i > shortLimit; i--) {
			tilesList.remove(i);
		}
	}

	@Override
	public int length() {
		return tilesList.size();
	}
}
