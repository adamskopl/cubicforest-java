package org.adamsko.cubicforest.world.objectsMasters.terrain;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class TerrainMaster extends WorldObjectsMasterDefault {

	private int atlasIndex;

	public TerrainMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("TerrainObjectsMaster", "tree", tilesMaster, textureName, tileW,
				tileH);

	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public org.adamsko.cubicforest.world.object.WorldObject factoryMethod(
			final Vector2 tilePos) {
		final Tree tree = new Tree(atlasRows.get(0)[0], 0, this);
		tree.setRenderVector(new Vector2(
				-atlasRows.get(0)[0].getRegionWidth() / 2, -5));
		final Vector2 pos = new Vector2(tilePos);
		pos.add(new Vector2(0.5f, 0.5f));
		tree.setTilesPos(pos);
		tree.setVerticalPos(0.5f);

		tree.setName("tree");
		return tree;
	};

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		atlasIndex = 0;
		for (final Vector2 pos : tilePositions) {
			addObject(factoryMethod(pos));
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
