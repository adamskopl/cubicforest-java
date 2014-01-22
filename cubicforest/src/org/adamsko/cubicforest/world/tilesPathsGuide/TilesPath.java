package org.adamsko.cubicforest.world.tilesPathsGuide;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilesMaster.Tile;

import com.badlogic.gdx.utils.Array;

/**
 * Holds +a {@link WorldObject} object and+ a queue of {@link Tile} objects
 * indicating path to be walked.
 * 
 * @author adamsko
 */
public class TilesPath {
	
	/**
	 * Object on the path.
	 */
	private WorldObject wanderer = null;
		
	/**
	 * List of tiles which wanderer has to go through.
	 */
	private List <Tile> tilesList;
	
	public TilesPath() {
		tilesList = new ArrayList<Tile>();		
	}
	
	/**
	 * Adds {@link Tile} object to the list.
	 * 
	 * @param newTile new {@link Tile} added to the end of the list.
	 */
	public void pushTile(Tile newTile) {
		tilesList.add(newTile);
	}

}
