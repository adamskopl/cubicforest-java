package org.adamsko.cubicforest.world.tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * A tile which is a base indicator of all objects positions in the world.
 * 
 * @author adamsko
 */
public class Tile extends WorldObject {

	private final List<WorldObject> occupants;

	/**
	 * For NullTile constructor.
	 */
	Tile() {
		super();
		occupants = null;
	}

	public Tile(final Vector2 coords, final TextureRegion tr,
			final WorldObjectsContainer container) {
		super(tr, 0, container, WorldObjectType.DEFAULT);
		this.tilesPos = coords;

		occupants = new ArrayList<WorldObject>();
	}

	/**
	 * Check if vector belongs to tile's area.
	 * 
	 * @param tilePos
	 *            vector being checked for inclusion
	 * @return
	 */
	public boolean isPosInTile(final Vector2 tilePos) {

		if (tilePos.x >= tilesPos.x && tilePos.x < tilesPos.x + 1) {
			if (tilePos.y >= tilesPos.y && tilePos.y < tilesPos.y + 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if tile is occupied by given object.
	 * 
	 * @param object
	 * @return
	 */
	public boolean isOccupied(final WorldObject object) {
		return occupants.contains(object);
	}

	public List<WorldObject> getOccupants() {
		return occupants;
	}

	public void addOccupant(final WorldObject insertObject,
			final boolean ignoreOccupation) throws Exception {
		occupants.add(insertObject);
		refresh();
	}

	/**
	 * Removes given occupant from 'occupants' collection. Removed occupant is
	 * no longer connected with this tile.
	 * 
	 * @param removedOccupant
	 */
	public void removeOccupant(final WorldObject removedOccupant) {
		if (!occupants.remove(removedOccupant)) {
			// if there was no 'removedOccupant' object in a collection
			Gdx.app.error("Tile::removeOccupant()",
					"no " + removedOccupant.getName() + " in 'occupants'");
		}
		refresh();
	}

	public void removeDeadOccupants() {
		final Iterator<WorldObject> iter = getOccupants().iterator();
		while (iter.hasNext()) {
			final WorldObject tileObject = iter.next();
			if (tileObject.getState() == WorldObjectState.DEAD) {
				tileObject.getParentContainer().removeObjectFromContainer(
						tileObject);
				iter.remove();
			}
		}
		refresh();
	}

	/**
	 * Does tile have any occupant?
	 * 
	 * @return
	 */
	public Boolean hasOccupant() {
		return !occupants.isEmpty();
	}

	/**
	 * Is tile valid for search algorithms (if it can be included in tile
	 * paths)? If at least one occupant is not valid for search algorithms, the
	 * whole tile is not.
	 * 
	 * @return
	 */
	public boolean isTilePathSearchValid() {
		for (final WorldObject occupant : getOccupants()) {
			if (!occupant.getTilePropertiesIndicator().isTilePathSearchValid()) {
				// one of the occupants is not valid - Tile is not valid
				return false;
			}
		}
		return true;
	}

	// /////////////
	// highlighting. other class needed.
	// /////////////
	public void refresh() {
		if (isTileHighlightedAsOccupied()) {
			getParentContainer().changeTexture(this, 0, 1);
		} else {
			getParentContainer().changeTexture(this, 0, 0);
		}
	}

	private boolean isTileHighlightedAsOccupied() {
		for (final WorldObject occupant : getOccupants()) {
			if (occupant.getTilePropertiesIndicator()
					.isTileHighlightedAsOccupied()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return getTilesPos().toString();
	}

}