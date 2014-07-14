package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.converter.TiledObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class EnemiesMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster, OrderableObjectsContainer {

	public EnemiesMaster(MapsLoader mapsLoader, TilesMaster TM, RoundsMaster roundsMaster, String textureName, int tileW, int tileH) {
		super("enemiesMaster", mapsLoader, TM, WorldObjectType.OBJECT_ENTITY, textureName, tileW, tileH);
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

	@Override
	public void loadMapObjects(MapsLoader mapsLoader) throws Exception {
		List<Vector2> coords = mapsLoader
				.getCoords(TiledObjectType_e.TILED_ENTITY_ENEMY);
		
		Enemy enemy;
		int atlasIndex = 0;
		for (Vector2 pos : coords) {
			enemy = new Enemy(atlasRows.get(0)[atlasIndex], atlasIndex);
			enemy.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -7));
			
			enemy.setSpeed(2);
			
			pos.add(new Vector2(0.5f, 0.5f));
			enemy.setTilesPos(pos);
			enemy.setName("E" + atlasIndex);
			enemy.setVerticalPos(0.3f);
			enemy.addLabel(ROLabel.LABEL_NAME);
			enemy.altLabelLast(Color.ORANGE, 1.0f, -10.0f, 10.0f);
			
			addObject(enemy);
			
			if(atlasIndex==2){atlasIndex=0;}else{atlasIndex++;}
		}
	}
	
	@Override
	public void unloadMapObjects(MapsLoader mapsLoader) throws Exception {
		removeWorldObjects();
	}
	
	public void removeEnemy(Enemy enemyToRemove) {
		try {
			removeObject(enemyToRemove);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
