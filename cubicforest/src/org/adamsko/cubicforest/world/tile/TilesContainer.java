package org.adamsko.cubicforest.world.tile;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TilesContainer extends WorldObjectsMasterDefault {

	public TilesContainer(final String name, final TilesMaster tilesMaster) {
		super(name, tilesMaster, "tiles-atlas-medium", 75, 45);
	}

	public List<RenderableObject> getTiles() {
		return getRenderableObjects(ROListType_e.RO_ALL);
	}

	/**
	 * Search Tiles for one containing given tilePosition.
	 * 
	 * @param tilePos
	 *            tilePosition of the searched Tile
	 * @return Tile which contains tilePos position. null if Tile not found
	 */
	public Tile getTileOnPos(final Vector2 tilePos) {
		for (final WorldObject wo : getWorldObjects()) {
			final Tile tile = (Tile) wo;
			if (tile.isPosInTile(tilePos)) {
				return tile;
			}
		}
		return NullCubicTile.instance();
	}

	public Tile getTileWithObject(final WorldObject object) {
		for (final WorldObject wo : getWorldObjects()) {
			final Tile tile = (Tile) wo;
			if (tile.isOccupied(object)) {
				return tile;
			}
		}
		return NullCubicTile.instance();
	}

	public void addTile(final Vector2 tilePos) {
		final Tile newTile = new CubicTile(tilePos, atlasRows.get(0)[0], this);
		newTile.setRenderVector(new Vector2(-atlasRows.get(0)[0]
				.getRegionWidth() / 2, -atlasRows.get(0)[0].getRegionHeight()));
		// tiles are slightly lower than other objects
		newTile.setVerticalPos(-0.01f);

		// FIXME: don't add newTile through addRenderableObject(), because it's
		// a Tile. Is it ok?
		super.getWorldObjects().add(newTile);
		super.getRenderableObjects(ROListType_e.RO_ALL).add(newTile);
		super.getRenderableObjects(ROListType_e.RO_UNSERVED).add(newTile);
	}

	@Override
	public void update(final float deltaTime) {
		// TODO Auto-generated method stub
	}

	public void clearTilesLabels() {
		for (final WorldObject wo : getWorldObjects()) {
			final Tile tile = (Tile) wo;
			tile.clearLabels();
		}
	}

	@Override
	public void loadMapObjects(final CFMap map) {

		final List<Vector2> coords = map
				.getObjectTypeCoords(TiledObjectType.TILED_TILE);

		for (final Vector2 vec : coords) {
			addTile(vec);
		}
	}

	@Override
	public void unloadMapObjects() throws Exception {
		while (getWorldObjects().size() != 0) {
			final WorldObject tile = getWorldObjects().get(0);
			getWorldObjects().remove(tile);
			removeRenderableObject(tile);
		}
	}

	public void debugPrintOccupants(final boolean printParticular) {
		int number = 0;
		for (final WorldObject t : getWorldObjects()) {
			final Tile tile = (Tile) t;
			for (final WorldObject o : tile.getOccupants()) {
				if (printParticular) {
					Gdx.app.debug("tile occupant", o.getName());
				}
				number++;
			}
		}
		Gdx.app.debug("tile occupant total", Integer.toString(number));
	}
}