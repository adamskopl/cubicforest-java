package org.adamsko.cubicforest.world.tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class CubicTile extends CubicObject implements Tile {

	private final List<WorldObject> occupants;

	/**
	 * For NullTile constructor.
	 */
	CubicTile() {
		super();
		occupants = null;
	}

	public CubicTile(final Vector2 coords, final TextureRegion tr,
			final WorldObjectsMasterDefault container) {
		super(tr, 0, container, WorldObjectType.DEFAULT);
		this.tilesPos = coords;

		occupants = new ArrayList<WorldObject>();
	}

	@Override
	public boolean isPosInTile(final Vector2 checkedPos) {
		if (checkedPos.x >= tilesPos.x && checkedPos.x < tilesPos.x + 1) {
			if (checkedPos.y >= tilesPos.y && checkedPos.y < tilesPos.y + 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPosInTile(final float x, final float y) {
		if (x >= tilesPos.x && x < tilesPos.x + 1) {
			if (y >= tilesPos.y && y < tilesPos.y + 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isOccupied(final WorldObject object) {
		return occupants.contains(object);
	}

	@Override
	public List<WorldObject> getOccupants() {
		return occupants;
	}

	@Override
	public void addOccupant(final WorldObject insertObject) {
		occupants.add(insertObject);
		refreshTexture();
	}

	@Override
	public void removeOccupant(final WorldObject occupantToRemove) {
		if (!occupants.remove(occupantToRemove)) {
			// if there was no 'removedOccupant' object in a collection
			Gdx.app.error("Tile::removeOccupant()",
					"no " + occupantToRemove.getName() + " in 'occupants'");
		}
		refreshTexture();
	}

	@Override
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
		refreshTexture();
	}

	@Override
	public boolean hasOccupant() {
		return !occupants.isEmpty();
	}

	@Override
	public boolean isTilePathSearchValid() {
		for (final WorldObject occupant : getOccupants()) {
			if (!occupant.getTilePropertiesIndicator().getTilePathSearchValid()) {
				// one of the occupants is not valid - Tile is not valid
				return false;
			}
		}
		return true;
	}

	@Override
	public void refreshTexture() {
		if (isTileHighlightedAsOccupied()) {
			getParentContainer().changeTexture(this, 2, 1);
		} else {
			getParentContainer().changeTexture(this, 0, 0);
		}
	}

	private boolean isTileHighlightedAsOccupied() {
		for (final WorldObject occupant : getOccupants()) {
			if (occupant.getTilePropertiesIndicator()
					.getTileHighlightedAsOccupied()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isTileValidSearchParameter(
			final TilesSearchParameter tilesSearchParameter) {
		// check if tile properties indicator of every tile is compatible with
		// given search parameter
		for (final WorldObject occupant : getOccupants()) {
			final TilePropertiesIndicator tilePropertiesIndicator = occupant
					.getTilePropertiesIndicator();
			if (!tilesSearchParameter
					.tilePropertiesValid(tilePropertiesIndicator)) {
				// one of the properties indicator is not compatible with search
				// parameter
				return false;
			}
		}
		return true;
	}
}