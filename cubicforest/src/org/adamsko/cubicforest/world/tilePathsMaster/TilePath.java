package org.adamsko.cubicforest.world.tilePathsMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;

import com.badlogic.gdx.Gdx;

/**
 * Holds +a {@link WorldObject} object and+ a queue of {@link Tile} objects
 * indicating path to be walked.
 * 
 * @author adamsko
 */
public class TilePath {

	/**
	 * List of tiles which wanderer has to go through.
	 */
	private List<Tile> tilesList;

	public TilePath() {
		tilesList = new ArrayList<Tile>();
	}

	/**
	 * Adds {@link Tile} object to the list.
	 * 
	 * @param newTile
	 *            new {@link Tile} added to the end of the list.
	 */
	public void pushTile(Tile newTile) {
		tilesList.add(newTile);
	}

	/**
	 * Add new {@link Tile} object in the front of the list.
	 * 
	 * @param newTile
	 */
	public void addTileFront(Tile newTile) {
		tilesList.add(0, newTile);
	}

	public Boolean isEmpty() {
		return tilesList.isEmpty();
	}

	public Tile removeFrontTile() {
		try {
			return tilesList.remove(0);
		} catch (IndexOutOfBoundsException ex) {
			Gdx.app.error("TilePath", ex.toString());
			return null;
		}
	}

	public Tile getFrontTile() {
		try {
			return tilesList.get(0);
		} catch (IndexOutOfBoundsException ex) {
			Gdx.app.error("TilePath", ex.toString());
			return null;
		}
	}

	public String toString() {
		String ret = new String();
		for (Tile t : tilesList) {
			ret += t.toString() + " ";
		}
		return ret;
	}

	/**
	 * Shorten path to given length (remove tiles exceeding the given number)
	 */
	public void shortenPath(int shortLimit) {
		if(length() <= shortLimit) {
			return;
		}
		for(int i = length()-1; i > shortLimit; i--) {
			tilesList.remove(i);
		}
	}

	public int length() {
		return tilesList.size();
	}
}
