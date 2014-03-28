package org.adamsko.cubicforest.world.objectsMasters;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class EnemiesMaster extends RenderableObjectsContainer implements
		RenderableObjectsMaster, OrderableObjectsContainer {

	public EnemiesMaster(TilesMaster TM, String textureName, int tileW, int tileH) {
		super(TM, textureName, tileW, tileH);
		try {
			addTestObjects();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(float deltaTime) {

	}

	@Override
	public List<WorldObject> getOrderableObjects() {
		return getWorldObjects();
	}
	
	private void addTestObjects() throws Exception {
		List<Vector2> testPositions = new ArrayList<Vector2>();
		testPositions.add(new Vector2(1, 1));
		testPositions.add(new Vector2(3, 9));
		testPositions.add(new Vector2(5, 9));
		testPositions.add(new Vector2(6, 9));

		RenderableObject testEnemy;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {
			testEnemy = new RenderableObject(atlasRows.get(0)[atlasIndex], atlasIndex, WorldObjectType_e.OBJECT_ENEMY);
			testEnemy.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -7));
			
			testEnemy.setSpeed(2);
			
			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));
			testEnemy.setTilesPos(pos);
			testEnemy.setName("enemy " + atlasIndex);
//			testEnemy.addLabel(ROLabel_e.LABEL_TILEPOS);
			testEnemy.addLabel(ROLabel_e.LABEL_NAME);
			testEnemy.altLabelLast(Color.ORANGE, 1.0f, -25.0f, 0.0f);
			
			addRenderableObject(testEnemy, this);
			
			if(atlasIndex==2){atlasIndex=0;}else{atlasIndex++;}
		}
	}

}
