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
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class HeroesMaster extends RenderableObjectsContainer implements RenderableObjectsMaster, OrderableObjectsContainer {

	public HeroesMaster(TilesMaster TM, String textureName, int tileW, int tileH) {
		super(TM, textureName, tileW, tileH);
		try {
		addTestObjects();
		} catch (Exception e) {
			Gdx.app.error("HeroesMaster", e.toString());
		}
	}
	
	private void addTestObjects() throws Exception {
		List<Vector2> testPositions = new ArrayList<Vector2>();
		testPositions.add(new Vector2(0, 5));
		testPositions.add(new Vector2(6, 4));
//		testPositions.add(new Vector2(3, 6));

		RenderableObject testPig;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {
			testPig = new RenderableObject(atlasRows.get(0)[atlasIndex], atlasIndex, WorldObjectType_e.OBJECT_HERO);
			testPig.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -7));
			
			testPig.setSpeed(4);
			
			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));
			testPig.setTilesPos(pos);
			testPig.setName("testPig");
			testPig.addLabel(ROLabel_e.LABEL_TILEPOS);
//			testPig.addLabel(ROLabel_e.LABEL_NAME);
			testPig.altLabelLast(Color.ORANGE, 1.0f, -25.0f, 0.0f);
			
			addRenderableObject(testPig, this);
			
			if(atlasIndex==2){atlasIndex=0;}else{atlasIndex++;}
		}
	}

	public void handleServantTileEvent(WorldObject servant,
			TileEvent_e tileEvent) {
		switch (tileEvent) {
		case TILE_PICKED: {
			RenderableObject servantConv = (RenderableObject) servant;
			int texNum = servantConv.getTexNum();
//			servantConv.setTextureRegion(atlasRows.get(1)[texNum]);
		}
		default: {

		}
		}
	}
	
	public WorldObject getTestObject() {
		return getWorldObjects().get(0);
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<WorldObject> getOrderableObjects() {
		return getWorldObjects();
	}

}
