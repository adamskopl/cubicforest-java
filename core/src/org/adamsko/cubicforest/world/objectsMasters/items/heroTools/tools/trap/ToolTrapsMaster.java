package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap;

import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.graphics.Color;
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
		// TODO Auto-generated method stub
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		HeroToolTrap trap;
		int nameIndex = 0;
		for (final Vector2 pos : tilePositions) {
			trap = new HeroToolTrap(atlasRows.get(0)[0], 0, this);
			trap.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -5));

			pos.add(new Vector2(0.5f, 0.5f));
			trap.setTilesPos(pos);
			trap.setName("H" + nameIndex);
			trap.addLabel(ROLabel.LABEL_NAME);
			trap.altLabelLast(Color.ORANGE, 1.0f, -10.0f, 10.0f);
			trap.setVerticalPos(0.3f);

			addObject(trap);

			nameIndex++;
		}
	}

	@Override
	public void loadMapObjects(final CFMap map) throws Exception {
		final List<Vector2> tilePositions = map
				.getObjectTypeCoords(TiledObjectType.TILED_ENTITY_HERO);

		loadMapObjects(tilePositions);

	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

}
