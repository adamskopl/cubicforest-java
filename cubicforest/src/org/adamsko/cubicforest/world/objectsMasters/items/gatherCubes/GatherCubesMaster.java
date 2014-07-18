package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.MapsLoaderTiled;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class GatherCubesMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster {

	private GatherCubesCounter gatherCubesCounter;

	public GatherCubesMaster(TilesMaster TM, String textureName, int tileW,
			int tileH) {
		super("GatherCubesMaster", TM, WorldObjectType.OBJECT_ITEM,
				textureName, tileW, tileH);
	}

	public GatherCubesCounter getGatherCubesCounter() {
		return gatherCubesCounter;
	}

	/**
	 * Check if counter allows heroTool to be build (if player can afford it).
	 */
	public Boolean isToolAffordable(HeroToolType heroToolType) {
		int toolCost = HeroesToolsMaster.heroTooltypeToCost(heroToolType);
		if (toolCost <= gatherCubesCounter.getCounter()) {
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaTime) {

	}

	public void initGatherCubesCounter(TilesMaster tilesMaster) {
		gatherCubesCounter = new GatherCubesCounter(tilesMaster,
				"cubes-atlas-medium", 25, 25, 650, -50);
	}

	public void removeCube(GatherCube cubeToRemove) {
		try {
			removeObject(cubeToRemove);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void counterAddValue(Integer value) {
		gatherCubesCounter.addValue(value);
	}

	@Override
	public void loadMapObjects(CFMap map) {
		List<Vector2> coords = map
				.getObjectTypeCoords(TiledObjectType.TILED_ITEM_GATHERCUBE);

		GatherCube gatherCube;
		int atlasIndex = 0;
		for (Vector2 pos : coords) {
			gatherCube = new GatherCube(atlasRows.get(0)[atlasIndex],
					atlasIndex);
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

	void cubeHighlight(GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(1)[cube.getTexNum()]);
	}

	void cubeUnHighlight(GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(0)[cube.getTexNum()]);
	}

}
