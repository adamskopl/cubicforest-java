package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.exit;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolStates;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class ToolExitsMaster extends WorldObjectsMasterDefault {

	public ToolExitsMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("ToolExitsMaster", tilesMaster, textureName, tileW, tileH);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public void loadMapObjects(final CFMap cfMap) throws Exception {
		final List<Vector2> tilePositions = cfMap
				.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_EXIT);

		loadMapObjects(tilePositions);
	}

	@Override
	public WorldObject factoryMethod(final Vector2 tilePos) {
		final HeroToolExit exit;
		exit = new HeroToolExit(atlasRows.get(0)[0], 0, this);
		exit.setState(HeroToolStates.STATE_READY);
		exit.setRenderVector(new Vector2(
				-atlasRows.get(0)[0].getRegionWidth() / 2 + 2, -11));

		final Vector2 pos = new Vector2(tilePos);
		pos.add(new Vector2(0.5f, 0.5f));

		exit.setTilesPos(pos);
		exit.setVerticalPos(0.1f);

		exit.addLabel(exit.getType().toString());
		exit.altLabelLast(Color.YELLOW, 0.8f, -40.0f, -10.0f);

		return exit;
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		for (final Vector2 pos : tilePositions) {
			addObject(factoryMethod(pos));
		}
	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

}
