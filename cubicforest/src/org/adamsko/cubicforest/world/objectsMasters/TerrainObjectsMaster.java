package org.adamsko.cubicforest.world.objectsMasters;

import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.terrain.Tree;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TerrainObjectsMaster extends WorldObjectsContainer implements
		WorldObjectsMaster {

	public TerrainObjectsMaster(TilesMaster TM, String textureName, int tileW,
			int tileH) {
		super("TerrainObjectsMaster", TM, WorldObjectType.OBJECT_TERRAIN,
				textureName, tileW, tileH);

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadMapObjects(CFMap map) {
		List<Vector2> coords = map
				.getObjectTypeCoords(TiledObjectType.TILED_TERRAIN);

		Tree tree;
		int atlasIndex = 0;
		for (Vector2 pos : coords) {
			tree = new Tree(atlasRows.get(0)[atlasIndex], atlasIndex);
			tree.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -5));
			pos.add(new Vector2(0.5f, 0.5f));
			tree.setTilesPos(pos);
			tree.setVerticalPos(0.5f);

			tree.setName("tree");

			addObject(tree);
			if (atlasIndex == 1) {
				atlasIndex = 0;
			} else {
				atlasIndex++;
			}
		}
	}

	@Override
	public void unloadMapObjects() throws Exception {
		removeWorldObjects();
	}

}
