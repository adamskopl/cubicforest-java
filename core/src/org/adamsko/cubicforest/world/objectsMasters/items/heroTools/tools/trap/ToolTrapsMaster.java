package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolStates_e;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class ToolTrapsMaster extends WorldObjectsMasterDefault {

	public ToolTrapsMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("ToolTrapsMaster", tilesMaster, textureName, tileW, tileH);

		// heroToolsFactory = new HeroToolsFactory(atlasRows, heroesMaster);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		HeroToolTrap trap;
		for (final Vector2 pos : tilePositions) {
			trap = new HeroToolTrap(atlasRows.get(0)[0], 0, this);
			trap.setState(HeroToolStates_e.STATE_READY);
			trap.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2 + 2, -11));

			pos.add(new Vector2(0.5f, 0.5f));

			trap.setTilesPos(pos);
			trap.setVerticalPos(0.1f);

			trap.setName("trap");

			addObject(trap);
		}
	}

	@Override
	public void loadMapObjects(final CFMap map) throws Exception {
		final List<Vector2> tilePositions = map
				.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_TRAP);

		loadMapObjects(tilePositions);

	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

}
