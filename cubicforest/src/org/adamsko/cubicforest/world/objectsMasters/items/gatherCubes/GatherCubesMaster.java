package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import com.badlogic.gdx.math.Vector2;

public class GatherCubesMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster {

	private GatherCubesCounter gatherCubesCounter;

	public GatherCubesCounter getGatherCubesCounter() {
		return gatherCubesCounter;
	}

	public GatherCubesMaster(TilesMaster TM, String textureName, int tileW,
			int tileH) {
		super(TM, WorldObjectType_e.OBJECT_GATHER_CUBE, textureName, tileW,
				tileH);
		try {
			addTestCubes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(float deltaTime) {

	}

	public void initGatherCubesCounter(TilesMaster tilesMaster) {
		gatherCubesCounter = new GatherCubesCounter(tilesMaster,
				WorldObjectType_e.OBJECT_GENERIC, "cubes-atlas-medium", 25, 25, 700, -50);
	}

	private void addTestCubes() throws Exception {
		List<Vector2> testPositions = new ArrayList<Vector2>();
		testPositions.add(new Vector2(0, 3));
		testPositions.add(new Vector2(4, 4));
		testPositions.add(new Vector2(8, 6));

		GatherCube testCube;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {
			testCube = new GatherCube(atlasRows.get(0)[atlasIndex], atlasIndex,
					WorldObjectType_e.OBJECT_GATHER_CUBE);
			testCube.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -2));

			testCube.setSpeed(2);
			testCube.setOccupiesTile(false);

			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));
			testCube.setTilesPos(pos);

			addWorldObject(testCube, this);

			if (atlasIndex == 3) {
				atlasIndex = 0;
			} else {
				atlasIndex++;
			}
		}

	}

	@Override
	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) {

		GatherCube tileCube = (GatherCube) eventTile.getItem();

		switch (evenType) {
		case OCCUPANT_ENTERS:
			break;

		case OCCUPANT_LEAVES:
			tileCube.setTextureRegion(atlasRows.get(0)[tileCube.getTexNum()]);
			break;
		case OCCUPANT_STOPS:
			tileCube.setTextureRegion(atlasRows.get(1)[tileCube.getTexNum()]);
			eventObject.setName("xx");
			gatherCubesCounter.addValue(1);
			break;
		default:
			break;
		}

	}

}
