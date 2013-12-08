package org.adamsko.cubicforest.world.tilesMaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.Tile;
import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.Gdx;
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
public class TilesMaster extends RenderableObjectsContainer implements RenderableObjectsMaster {

	// number of tiles (mapSize = 16 -> 4x4 tiles)
	private int mapSize;
	
	public TilesMaster(int mapSize) {
		super("tiles-atlas", 50, 31);
		this.mapSize = mapSize;
		TilesMasterHelper.setMapSize(mapSize);
		initTiles();
	}
	
	public void initTiles() {		
		for(int fIndex = 0; fIndex < mapSize; fIndex++) {
			if(TilesMasterHelper.isTileonTestMap(fIndex)) {continue;}
			
			Vector2 fCoords = TilesMasterHelper.calcCoords(fIndex);
			Gdx.app.log("coord", fCoords.x + ", " + fCoords.y);
			Tile newTile = new Tile(fCoords, atlas[1]);
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
	
}