package org.adamsko.cubicforest.world.tilesMaster;

import java.util.List;

import org.adamsko.cubicforest.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TilesContainer extends RenderableObjectsContainer implements RenderableObjectsMaster {
	
	public TilesContainer(TilesMaster TM) {
		super(TM, "tiles-atlas-medium", 75, 45);
	}
	
	public List<RenderableObject> getTiles() {
		return getRenderableObjects(ROListType.RO_ALL);
	}
	
	/**
	 * Search Tiles for one containing given tilePosition.
	 * @param tilePos tilePosition of the searched Tile  
	 * @return Tile which contains tilePos position.
	 */
	public Tile getTileOnPos(Vector2 tilePos) {
		
		for(WorldObject wo : getWorldObjects()) {
			Tile tile = (Tile)wo;
			if(tile.isPosInTile(tilePos)){
				return tile;
			}
		}
		
		Gdx.app.error("TilesContainer", "tile with (" + tilePos.x + ", " + tilePos.y + ") not found");
		return null;
	}
	
	public void addTile(Vector2 tilePos) {
		Tile newTile = new Tile(tilePos, atlasRows.get(0)[0]);
		newTile.setRenderVector(new Vector2(-atlasRows.get(0)[0].getRegionWidth() / 2,
				-atlasRows.get(0)[0].getRegionHeight()));
		// tiles are slightly lower than other objects
		newTile.setHeight(-0.01f);

		// FIXME: don't add newTile through addRenderableObject(), because it's
		// a Tile. Is it ok?
		super.getWorldObjects().add(newTile);
		super.getRenderableObjects(ROListType.RO_ALL).add(newTile);
		super.getRenderableObjects(ROListType.RO_UNSERVED).add(newTile);
	}

	/**
	 * Mark given tile to be different from the others.
	 * 
	 * @param tileToHighlight
	 * 
	 */
	public void testHighlightTile(Tile tileToHighlight) {
		tileToHighlight.setTextureRegion(atlasRows.get(0)[2]);
	}
	
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {
		for(WorldObject wo : getRenderableObjects(ROListType.RO_ALL)) {
			Tile t = (Tile)wo;
			if(t.isPosInTile(inputTilesPos)) {
				t.setTextureRegion(atlasRows.get(0)[1]);
			}
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
	}
	
}