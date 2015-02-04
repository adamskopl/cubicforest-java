package org.adamsko.cubicforest.world.tile;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMemento;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoDefault;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoState;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoStateDefault;
import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class TilesContainer extends WorldObjectsMasterDefault {

	public TilesContainer(final String name, final TilesMaster tilesMaster) {
		super(name, tilesMaster, "tiles-atlas-medium", 75, 45);
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

	/**
	 * Search Tiles for one containing given tilePosition.
	 * 
	 * @param tilePos
	 *            tilePosition of the searched Tile
	 * @return Tile which contains tilePos position. null if Tile not found
	 */
	public Tile getTileOnPos(final float posX, final float posY) {
		for (final WorldObject wo : getWorldObjects()) {
			final Tile tile = (Tile) wo;
			if (tile.isPosInTile(posX, posY)) {
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
		// tiles are slightly lower than other objects (should be rendered
		// first)
		newTile.setVerticalPos(-0.01f);

		// uncomment to add a position label to the tile
		addTilesPosLabel(newTile);

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
	public void loadMapObjects(final List<Vector2> tilePositions) {
		for (final Vector2 vec : tilePositions) {
			addTile(vec);
		}

	}

	@Override
	public void loadMapObjects(final CFMap map) {

		final List<Vector2> tilePositions = map
				.getObjectTypeCoords(TiledObjectType.TILED_TILE);
		loadMapObjects(tilePositions);

	}

	@Override
	public void unloadMapObjects() {
		while (getWorldObjects().size() != 0) {
			final WorldObject tile = getWorldObjects().get(0);
			getWorldObjects().remove(tile);
			removeRenderableObject(tile);
		}
	}

	public void debugPrintOccupants(final boolean printParticular) {
		for (final WorldObject t : getWorldObjects()) {
			final Tile tile = (Tile) t;
			for (final WorldObject o : tile.getOccupants()) {
				if (printParticular) {
					Gdx.app.debug("tile occupant", o.getName());
				}
			}
		}
	}

	public void addTilesPosLabel(final Tile tile) {
		tile.addLabel(ROLabel.LABEL_TILEPOS);
		tile.altLabelLast(Color.RED, 0.7f, -17.0f, -20.0f);
	}

	@Override
	public WOMMemento createMemento() {
		final WOMMementoState state = new WOMMementoStateDefault(this);
		final WOMMemento memento = new WOMMementoDefault();
		memento.setState(state);
		return memento;
	}
}