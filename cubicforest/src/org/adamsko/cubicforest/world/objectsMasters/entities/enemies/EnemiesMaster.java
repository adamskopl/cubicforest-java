package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.converter.TiledObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class EnemiesMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster, OrderableObjectsContainer {

	public EnemiesMaster(MapsLoader mapsLoader, TilesMaster TM, String textureName, int tileW, int tileH) {
		super(mapsLoader, TM, WorldObjectType_e.OBJECT_ENTITY, textureName, tileW, tileH);
		try {
			loadMapObjects(mapsLoader);
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

		Enemy testEnemy;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {
			testEnemy = new Enemy(atlasRows.get(0)[atlasIndex], atlasIndex);
			testEnemy.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -7));
			
			testEnemy.setSpeed(2);
			
			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));
			testEnemy.setTilesPos(pos);
			testEnemy.setName("enemy " + atlasIndex);
//			testEnemy.addLabel(ROLabel_e.LABEL_TILEPOS);
//			testEnemy.addLabel(ROLabel_e.LABEL_NAME);
//			testEnemy.altLabelLast(Color.ORANGE, 1.0f, -25.0f, 0.0f);
			
			addObject(testEnemy);
			
			if(atlasIndex==2){atlasIndex=0;}else{atlasIndex++;}
		}
	}

	@Override
	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadMapObjects(MapsLoader mapsLoader) throws Exception {
		List<Vector2> coords = mapsLoader
				.getCoords(TiledObjectType_e.TILED_ENEMY);
		
		Enemy enemy;
		int atlasIndex = 0;
		for (Vector2 pos : coords) {
			enemy = new Enemy(atlasRows.get(0)[atlasIndex], atlasIndex);
			enemy.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -7));
			
			enemy.setSpeed(2);
			
			pos.add(new Vector2(0.5f, 0.5f));
			enemy.setTilesPos(pos);
			enemy.setName("Enemy" + atlasIndex);
			enemy.addLabel(ROLabel_e.LABEL_NAME);
			
			addObject(enemy);
			
			if(atlasIndex==2){atlasIndex=0;}else{atlasIndex++;}
		}
	}
}
