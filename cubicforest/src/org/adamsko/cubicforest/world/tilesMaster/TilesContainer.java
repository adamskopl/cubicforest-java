package org.adamsko.cubicforest.world.tilesMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TilesContainer extends RenderableObjectsContainer implements
		RenderableObjectsMaster {

	public TilesContainer(TilesMaster TM) {
		super(TM, "tiles-atlas-medium", 75, 45);
	}

	public List<RenderableObject> getTiles() {
		return getRenderableObjects(ROListType_e.RO_ALL);
	}

	/**
	 * Search Tiles for one containing given tilePosition.
	 * 
	 * @param tilePos
	 *            tilePosition of the searched Tile
	 * @return Tile which contains tilePos position.
	 */
	public Tile getTileOnPos(Vector2 tilePos) {

		for (WorldObject wo : getWorldObjects()) {
			Tile tile = (Tile) wo;
			if (tile.isPosInTile(tilePos)) {
				return tile;
			}
		}

		Gdx.app.error("TilesContainer", "tile with (" + tilePos.x + ", "
				+ tilePos.y + ") not found");
		return null;
	}
	
	public Tile getTileWithObject(WorldObject object) {
		for(WorldObject wo : getWorldObjects()) {
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
	 * @param tileToHighlight tile to be changed.
	 * @param texNum sequence number of the texture in an atlas row.
	 */
	public void testHighlightTile(Tile tileToHighlight, int texNum) {
		tileToHighlight.setTextureRegion(atlasRows.get(0)[texNum]);
	}
		
	public List<Tile> getPathTestTiles() {
		List<Tile> testList = new ArrayList<Tile>();
		
		List<Vector2> posList = new ArrayList<Vector2>();
		posList.add(new Vector2(7, -3));
		posList.add(new Vector2(8, -3));
		posList.add(new Vector2(9, -3));
		posList.add(new Vector2(10, -3));
		posList.add(new Vector2(10, -2));
		posList.add(new Vector2(10, -1));
		posList.add(new Vector2(10, 0));
		posList.add(new Vector2(9, 0));
		posList.add(new Vector2(8, 0));
		posList.add(new Vector2(8, 1));
		posList.add(new Vector2(8, 2));
		posList.add(new Vector2(9, 2));
		posList.add(new Vector2(10, 2));
		posList.add(new Vector2(10, 1));
		posList.add(new Vector2(10, 0));
		posList.add(new Vector2(10, -1));
		posList.add(new Vector2(10, -2));
		posList.add(new Vector2(11, -2));
		posList.add(new Vector2(12, -2));

		for(Vector2 p : posList) {
			Tile posTile = getTileOnPos(p);
			if(posTile == null){
				Gdx.app.error("testPath", "null tile");
				continue;
			}			
			testList.add(posTile);
		}
		
		return testList;
	}
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
	}

}