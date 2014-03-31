package org.adamsko.cubicforest.world.tilesMaster;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;

import com.badlogic.gdx.math.Vector2;

public class TilesContainer extends WorldObjectsContainer implements
		WorldObjectsMaster {
	
	public TilesContainer(TilesMaster TM) {
		super(TM, WorldObjectType_e.OBJECT_TILE, "tiles-atlas-medium", 75, 45);
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
	public Tile getTileOnPos(Vector2 tilePos) {

		for (WorldObject wo : getWorldObjects()) {
			Tile tile = (Tile) wo;
			if (tile.isPosInTile(tilePos)) {
				return tile;
			}
		}
		return null;
	}

	public Tile getTileWithObject(WorldObject object) {
		for (WorldObject wo : getWorldObjects()) {
			Tile tile = (Tile) wo;
			if (tile.getOccupant() == object) {
				return tile;
			}
		}
		return null;
	}

	public void addTile(Vector2 tilePos) {
		Tile newTile = new Tile(tilePos, atlasRows.get(0)[0]);
		newTile.setRenderVector(new Vector2(-atlasRows.get(0)[0]
				.getRegionWidth() / 2, -atlasRows.get(0)[0].getRegionHeight()));
		// tiles are slightly lower than other objects
		newTile.setHeight(-0.01f);

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
	public void testHighlightTile(Tile tileToHighlight, int texNum) {
		tileToHighlight.setTextureRegion(atlasRows.get(0)[texNum]);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
	}
	
	public void clearTilesLabels() {
		for (WorldObject wo : getWorldObjects()) {
			Tile tile = (Tile) wo;
			tile.clearLabels();
		}
	}

}