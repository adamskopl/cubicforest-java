package org.adamsko.cubicforest.world.objectsMasters;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class HeroesMaster extends RenderableObjectsContainer implements RenderableObjectsMaster {

	public HeroesMaster(TilesMaster TM, String textureName, int tileW, int tileH) {
		super(TM, textureName, tileW, tileH);
		addTestObjects();
	}
	
	private void addTestObjects() {
		List<Vector2> testPositions = new ArrayList<Vector2>();
		testPositions.add(new Vector2(0, 0));
		testPositions.add(new Vector2(6, 4));
		testPositions.add(new Vector2(2, 5));

		RenderableObject testPig;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {
			testPig = new RenderableObject(atlas[atlasIndex]);
			testPig.setRenderVector(new Vector2(
					-atlas[0].getRegionWidth() / 2, -7));
			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));
			testPig.setTilesPos(pos);
			addRenderableObject(testPig);
			
			if(atlasIndex==2){atlasIndex=0;}else{atlasIndex++;}
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
