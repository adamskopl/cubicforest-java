package org.adamsko.cubicforest.world.tile;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class TilesContainer extends WorldObjectsContainer {

	public TilesContainer(final String name, final TilesMaster TM) {
		super(name, WorldObjectType.OBJECT_UNDEFINED, TM, "tiles-atlas-medium",
				75, 45);
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
		return NullTile.instance();
	}

	public Tile getTileWithObject(final WorldObject object) {
		for (final WorldObject wo : getWorldObjects()) {
			final Tile tile = (Tile) wo;
			if (Tile.occupantsRefactor) {
				if (tile.isOccupied(object)) {
					return tile;
				}
			} else {
				if (tile.getOccupant() == object) {
					return tile;
				}
			}
		}
		return NullTile.instance();
	}

	public void addTile(final Vector2 tilePos) {
		final Tile newTile = new Tile(tilePos, atlasRows.get(0)[0], this);
		newTile.setRenderVector(new Vector2(-atlasRows.get(0)[0]
				.getRegionWidth() / 2, -atlasRows.get(0)[0].getRegionHeight()));
		// tiles are slightly lower than other objects
		newTile.setVerticalPos(-0.01f);

		newTile.addLabel(newTile.getTilesPos().toString());
		newTile.altLabelLast(Color.WHITE, 0.8f, -20.0f, -10.0f);

		// FIXME: don't add newTile through addRenderableObject(), because it's
		// a Tile. Is it ok?
		super.getWorldObjects().add(newTile);
		super.getRenderableObjects(ROListType_e.RO_ALL).add(newTile);
		super.getRenderableObjects(ROListType_e.RO_UNSERVED).add(newTile);
	}

	/**
	 * Change given tile's texture to a flashy one.
	 * 
	 * @param tileToHighlight
	 *            tile to be changed.
	 * @param texNum
	 *            sequence number of the texture in an atlas row.
	 */
	public void testHighlightTile(final Tile tileToHighlight, final int texRow,
			final int texCol) {
		tileToHighlight.setTextureRegion(atlasRows.get(texRow)[texCol]);
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

}