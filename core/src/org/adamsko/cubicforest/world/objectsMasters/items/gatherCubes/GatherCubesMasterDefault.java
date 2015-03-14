package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMemento;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoDefault;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
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
		super("GatherCubesMaster", "gatherCube", tilesMaster, textureName,
				tileW, tileH);
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
	public WorldObject factoryMethod(final Vector2 tilePos) {
		final GatherCube gatherCube;
		gatherCube = new GatherCube(atlasRows.get(0)[0], 0, this);
		gatherCube.setTextureRegionCubic(cubicTextureRegion);
		gatherCube.setRenderVector(new Vector2(-atlasRows.get(0)[0]
				.getRegionWidth() / 2, -2));
		gatherCube.setRenderVectorCubic(new Vector2(-37.0f, -23.0f));

		gatherCube.setSpeed(2);
		gatherCube.setVerticalPos(0.5f);

		final Vector2 pos = new Vector2(tilePos);
		pos.add(new Vector2(0.5f, 0.5f));
		gatherCube.setTilesPos(pos);
		return gatherCube;
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		for (final Vector2 pos : tilePositions) {
			addObject(factoryMethod(pos));
		}

	}

	@Override
	public void loadMapObjects(final CFMap map) {

		final List<Vector2> tilePositions = map
				.getObjectTypeCoords(TiledObjectType.TILED_ITEM_GATHERCUBE);
		loadMapObjects(tilePositions);

		gatherCubesCounter.setStartingValue(map.getProperties()
				.getStartingCubes());

	}

	@Override
	public void unloadMapObjects() {
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

	@Override
	public WOMMemento createMemento() {
		final GatherCubesMementoState gatherCubesMementoState = new GatherCubesMementoState(
				this);
		final WOMMemento memento = new WOMMementoDefault();
		memento.setState(gatherCubesMementoState);
		return memento;
	}

	@Override
	public void setMemento(final WOMMemento memento) {
		final GatherCubesMementoState gatherCubesMementoState = (GatherCubesMementoState) memento
				.getState();
		gatherCubesCounter.setCounter((int) gatherCubesMementoState
				.getCounter().x);
		super.setMemento(memento);
	}

}
