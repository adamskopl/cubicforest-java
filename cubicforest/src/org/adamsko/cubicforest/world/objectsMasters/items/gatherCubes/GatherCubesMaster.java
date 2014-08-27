package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.CollisionObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class GatherCubesMaster extends CollisionObjectsMaster {

	private GatherCubesCounter gatherCubesCounter;

	public GatherCubesMaster(final TilesMaster TM, final String textureName,
			final int tileW, final int tileH) {
		super("GatherCubesMaster", TM, WorldObjectType.OBJECT_ITEM,
				textureName, tileW, tileH);
	}

	public GatherCubesCounter getGatherCubesCounter() {
		return gatherCubesCounter;
	}

	/**
	 * Check if counter allows heroTool to be build (if player can afford it).
	 */
	public Boolean isToolAffordable(final HeroToolType heroToolType) {
		final int toolCost = HeroesToolsMaster.heroTooltypeToCost(heroToolType);
		if (toolCost <= gatherCubesCounter.getCounter()) {
			return true;
		}
		return false;
	}

	@Override
	public void update(final float deltaTime) {

	}

	public void initGatherCubesCounter(final TilesMaster tilesMaster) {
		gatherCubesCounter = new GatherCubesCounter(tilesMaster,
				"cubes-atlas-medium", 25, 25, 650, -50);
	}

	public void removeCube(final GatherCube cubeToRemove) {
		try {
			removeObjectFromContainer(cubeToRemove);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void counterAddValue(final int value) {
		gatherCubesCounter.addValue(value);
	}

	@Override
	public void loadMapObjects(final CFMap map) {
		final List<Vector2> coords = map
				.getObjectTypeCoords(TiledObjectType.TILED_ITEM_GATHERCUBE);

		GatherCube gatherCube;
		int atlasIndex = 0;
		for (final Vector2 pos : coords) {
			gatherCube = new GatherCube(atlasRows.get(0)[atlasIndex],
					atlasIndex, this);
			gatherCube.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -2));

			gatherCube.setSpeed(2);
			gatherCube.setOccupiesTile(false);
			gatherCube.setVerticalPos(0.5f);

			pos.add(new Vector2(0.5f, 0.5f));
			gatherCube.setTilesPos(pos);

			addObject(gatherCube);

			if (atlasIndex == 3) {
				atlasIndex = 0;
			} else {
				atlasIndex++;
			}
		}

	}

	@Override
	public void unloadMapObjects() throws Exception {
		gatherCubesCounter.reset();
		removeWorldObjects();
	}

	public void cubeHighlight(final GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(1)[cube.getTexNum()]);
	}

	public void cubeUnHighlight(final GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(0)[cube.getTexNum()]);
	}

}
