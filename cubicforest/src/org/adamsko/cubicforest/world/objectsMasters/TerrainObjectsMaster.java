package org.adamsko.cubicforest.world.objectsMasters;

import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.converter.TiledObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.terrain.Tree;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TerrainObjectsMaster extends WorldObjectsContainer implements WorldObjectsMaster {

	public TerrainObjectsMaster(MapsLoader mapsLoader, TilesMaster TM, String textureName, int tileW,
			int tileH) {
		super("TerrainObjectsMaster", mapsLoader, TM, WorldObjectType.OBJECT_TERRAIN, textureName, tileW, tileH);
		try {
			loadMapObjects(mapsLoader);
		} catch (Exception e) {
			Gdx.app.error("TerrainObjectsMaster", e.toString());
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadMapObjects(MapsLoader mapsLoader) {
		List<Vector2> coords = mapsLoader
				.getCoords(TiledObjectType_e.TILED_TERRAIN);

		Tree tree;
		int atlasIndex = 0;
		for (Vector2 pos : coords) {
			tree = new Tree(atlasRows.get(0)[atlasIndex], atlasIndex);
			tree.setRenderVector(new Vector2(
					-atlasRows.get(0)[0].getRegionWidth() / 2, -5));
			pos.add(new Vector2(0.5f, 0.5f));
			tree.setTilesPos(pos);
			tree.setVerticalPos(0.5f);
			
			tree.setName("tree");
			
			addObject(tree);
			if(atlasIndex==1){atlasIndex=0;}else{atlasIndex++;}
		}
	}
	
	@Override
	public void unloadMapObjects(MapsLoader mapsLoader) throws Exception {
		removeWorldObjects();
	}

}
