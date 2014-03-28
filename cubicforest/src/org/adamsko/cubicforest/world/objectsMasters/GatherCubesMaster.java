package org.adamsko.cubicforest.world.objectsMasters;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GatherCubesMaster extends RenderableObjectsContainer implements
		RenderableObjectsMaster {

	public GatherCubesMaster(TilesMaster TM, String textureName, int tileW, int tileH) {
		super(TM, textureName, tileW, tileH);		
		try {
			addTestCubes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(float deltaTime) {
		
	}

	private void addTestCubes() throws Exception {
		List<Vector2> testPositions = new ArrayList<Vector2>();
		testPositions.add(new Vector2(0, 3));
		testPositions.add(new Vector2(4, 4));
		testPositions.add(new Vector2(8, 6));
		
		RenderableObject testCube;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {
			testCube = new RenderableObject(atlasRows.get(0)[atlasIndex], atlasIndex, WorldObjectType_e.OBJECT_GATHER_CUBE);
			testCube.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -2));
			
			testCube.setSpeed(2);
			testCube.setOccupiesTile(false);
			
			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));
			testCube.setTilesPos(pos);
			
			addRenderableObject(testCube, this);
			
			if(atlasIndex==3){atlasIndex=0;}else{atlasIndex++;}
		}
		
	}
	
	public void tileEvent() {
		
	}
	
}
