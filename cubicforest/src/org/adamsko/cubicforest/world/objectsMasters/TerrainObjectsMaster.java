package org.adamsko.cubicforest.world.objectsMasters;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.world.WorldObjectsContainer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class TerrainObjectsMaster extends WorldObjectsContainer implements WorldObjectsMaster {

	public TerrainObjectsMaster(TilesMaster TM, String textureName, int tileW,
			int tileH) {
		super(TM, WorldObjectType_e.OBJECT_TERRAIN, textureName, tileW, tileH);
		try {
		addTestObjects();
		} catch (Exception e) {
			Gdx.app.error("TerrainObjectsMaster", e.toString());
		}
	}
	
	private void addTestObjects() throws Exception {

		List<Vector2> testPositions = new ArrayList<Vector2>();
		testPositions.add(new Vector2(4, 0));
		testPositions.add(new Vector2(2, 2));
		testPositions.add(new Vector2(2, 4));
		testPositions.add(new Vector2(7, 7));
		testPositions.add(new Vector2(6, 2));
		testPositions.add(new Vector2(1, 7));
		testPositions.add(new Vector2(3, 2));

		WorldObject testTree;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {
			testTree = new WorldObject(atlasRows.get(0)[atlasIndex], atlasIndex, WorldObjectType_e.OBJECT_TERRAIN);
			testTree.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -5));
			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));
			testTree.setTilesPos(pos);
			
			testTree.setName("tree");
			testTree.addLabel(ROLabel_e.LABEL_NAME);
			testTree.altLabelLast(Color.ORANGE, 0.8f, -15.0f, 0.0f);
			
			addWorldObject(testTree, this);
			if(atlasIndex==1){atlasIndex=0;}else{atlasIndex++;}
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
