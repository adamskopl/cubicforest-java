package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.converter.TiledObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import com.badlogic.gdx.math.Vector2;

public class GatherCubesMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster {

	private GatherCubesCounter gatherCubesCounter;

	public GatherCubesMaster(MapsLoader mapsLoader, TilesMaster TM,
			String textureName, int tileW, int tileH) {
		super("GatherCubesMaster", mapsLoader, TM,
				WorldObjectType_e.OBJECT_ITEM, textureName, tileW, tileH);
		try {
			loadMapObjects(mapsLoader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GatherCubesCounter getGatherCubesCounter() {
		return gatherCubesCounter;
	}

	/**
	 * Check if counter allows heroTool to be build (if player can afford it).
	 */
	public Boolean isToolAffordable(HeroToolType_e heroToolType) {
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

	void removeCube(GatherCube cubeToRemove) {
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
	public void loadMapObjects(MapsLoader mapsLoader) {
		List<Vector2> coords = mapsLoader
				.getCoords(TiledObjectType_e.TILED_ITEM_GATHERCUBE);

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
	public void reload(MapsLoader mapsLoader) {
		gatherCubesCounter.reset();

		try {
			removeWorldObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}

		loadMapObjects(mapsLoader);
	}
	
	void cubeHighlight(GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(1)[cube.getTexNum()]);
	}
	
	void cubeUnHighlight(GatherCube cube) {
		cube.setTextureRegion(atlasRows.get(0)[cube.getTexNum()]);
	}	

}
