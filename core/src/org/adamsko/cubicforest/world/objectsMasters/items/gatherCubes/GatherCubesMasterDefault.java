package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class GatherCubesMasterDefault extends WorldObjectsMasterDefault
		implements Nullable, GatherCubesMaster {

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
	public void loadMapObjects(final CFMap map) {
		final List<Vector2> coords = map
				.getObjectTypeCoords(TiledObjectType.TILED_ITEM_GATHERCUBE);

		GatherCube gatherCube;
		for (final Vector2 pos : coords) {
			gatherCube = new GatherCube(atlasRows.get(0)[0], 0, this);
			gatherCube.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -2));

			gatherCube.setSpeed(2);
			gatherCube.setVerticalPos(0.5f);

			pos.add(new Vector2(0.5f, 0.5f));
			gatherCube.setTilesPos(pos);

			addObject(gatherCube);
		}

		gatherCubesCounter.setStartingValue(map.getProperties()
				.getStartingCubes());

	}

	@Override
	public void unloadMapObjects() throws Exception {
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
