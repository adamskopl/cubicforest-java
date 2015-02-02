package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.exit;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

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
	public void loadMapObjects(final List<Vector2> tilePositions) {
		HeroToolExit exit;
		for (final Vector2 pos : tilePositions) {
			exit = new HeroToolExit(atlasRows.get(0)[0], 0, this);
			exit.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2 + 2, -30));

			exit.setTilesPos(pos);
			exit.setVerticalPos(0.1f);

			addObject(exit);
		}
	}

	@Override
	public void unloadMapObjects() {
		// TODO Auto-generated method stub

	}

}
