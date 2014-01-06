package org.adamsko.cubicforest.world.tilesMaster;

import java.util.List;

import org.adamsko.cubicforest.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.math.Vector2;

public class TilesContainer extends RenderableObjectsContainer implements RenderableObjectsMaster, PickMasterClient {
	
	public TilesContainer() {
		super("tiles-atlas-medium", 75, 45);
	}
	
	public List<RenderableObject> getTiles() {
		return renderableObjects;
	}
	
	public void addTile(Vector2 tilePos) {
		Tile newTile = new Tile(tilePos, atlas[0]);
		worldObjects.add(newTile);
		renderableObjects.add(newTile);
	}

	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {
		for(WorldObject wo : renderableObjects) {
			Tile t = (Tile)wo;
			if(t.isPosInTile(inputTilesPos)) {
				t.setTextureRegion(atlas[1]);				
			}
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