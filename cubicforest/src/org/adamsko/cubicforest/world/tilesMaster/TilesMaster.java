package org.adamsko.cubicforest.world.tilesMaster;

import java.util.List;

import org.adamsko.cubicforest.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.math.Vector2;

/**
 * Tiles managing class.
 * Map's model: <br>
 * . X---> <br>
 * Y 0 1 2 <br>
 * | 3 4 5 <br>
 * V 6 7 8 <br>
 * @author adamsko
 *
 */
public class TilesMaster extends RenderableObjectsContainer implements RenderableObjectsMaster, PickMasterClient {

	// number of tiles (mapSize = 16 -> 4x4 tiles)
	private int mapSize;
	
	public TilesMaster(int mapSize) {
		super("tiles-atlas-medium", 75, 45);
		this.mapSize = mapSize;
		TilesMasterHelper.setMapSize(mapSize);
		initTiles();
	}
	
	public void initTiles() {		
		for(int fIndex = 0; fIndex < mapSize; fIndex++) {
			if(TilesMasterHelper.isTileonTestMap(fIndex)) {continue;}
			
			Vector2 fCoords = TilesMasterHelper.calcCoords(fIndex);	
			fCoords.add(new Vector2(7, -3)); // temporary solution for centering view
			Tile newTile = new Tile(fCoords, atlas[0]);
			worldObjects.add(newTile);
			renderableObjects.add(newTile);
		}
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<WorldObject> getWorldObjects() {
		return worldObjects;
	}
	
	@Override
	public List<RenderableObject> getRendarbleObjects() {
		return renderableObjects;
	}

	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {	
		
		for(WorldObject wo : worldObjects) {
			Tile t = (Tile)wo;
			if(t.isPosInTile(inputTilesPos)) {
				// tile picked
				t.setTextureRegion(atlas[1]);
			}
		}		
	}	
}