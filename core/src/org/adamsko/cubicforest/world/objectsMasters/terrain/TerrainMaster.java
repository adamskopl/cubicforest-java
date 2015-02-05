package org.adamsko.cubicforest.world.objectsMasters.terrain;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class TerrainMaster extends WorldObjectsMasterDefault {

	public TerrainMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("TerrainObjectsMaster", tilesMaster, textureName, tileW, tileH);

	}

	@Override
	public void update(final float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		// TODO Auto-generated method stub
		Tree tree;
		int atlasIndex = 0;
		for (final Vector2 pos : tilePositions) {
			tree = new Tree(atlasRows.get(0)[atlasIndex], atlasIndex, this);
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
	public void loadMapObjects(final CFMap map) {
		final List<Vector2> tilePositions = map
				.getObjectTypeCoords(TiledObjectType.TILED_TERRAIN);
		loadMapObjects(tilePositions);
	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

}
