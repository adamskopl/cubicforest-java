package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class GatherCubesMasterDefault extends WorldObjectsMasterDefault
		implements GatherCubesMaster {

	private GatherCubesCounter gatherCubesCounter;

	GatherCubesMasterDefault() {
		super(0);
	}

	public GatherCubesMasterDefault(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("GatherCubesMaster", tilesMaster, textureName, tileW, tileH);
		gatherCubesCounter = new GatherCubesCounterDefault(
				"cubes-atlas-medium", 25, 25, 650, -50);
		gatherCubesCounter.setStartingValue(0);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public GatherCubesCounter getGatherCubesCounter() {
		return gatherCubesCounter;
	}

	@Override
	public void update(final float deltaTime) {

	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		// TODO Auto-generated method stub
		GatherCube gatherCube;
		for (final Vector2 pos : tilePositions) {
			gatherCube = new GatherCube(atlasRows.get(0)[0], 0, this);
			gatherCube.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -2));

			gatherCube.setSpeed(2);
			gatherCube.setVerticalPos(0.5f);

			pos.add(new Vector2(0.5f, 0.5f));
			gatherCube.setTilesPos(pos);

			addObject(gatherCube);
		}

	}

	@Override
	public void loadMapObjects(final CFMap map) {

		final List<Vector2> tilePositions = map
				.getObjectTypeCoords(TiledObjectType.TILED_ITEM_GATHERCUBE);
		loadMapObjects(tilePositions);

		// FIXME: not included in loadMapObjects(List<Vector2>)
		gatherCubesCounter.setStartingValue(map.getProperties()
				.getStartingCubes());

	}

	@Override
	public void unloadMapObjects() {
		gatherCubesCounter.reset();
		removeWorldObjects();
	}

	@Override
	public void cubeHighlight(final GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(1)[cube.getTexNum()]);
	}

	@Override
	public void cubeUnHighlight(final GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(0)[cube.getTexNum()]);
	}

}
