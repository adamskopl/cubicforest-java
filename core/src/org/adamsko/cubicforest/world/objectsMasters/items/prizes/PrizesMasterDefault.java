package org.adamsko.cubicforest.world.objectsMasters.items.prizes;

import java.util.List;

import org.adamsko.cubicforest.gui.prizes.GuiPrizes;
import org.adamsko.cubicforest.gui.prizes.GuiPrizesDefault;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class PrizesMasterDefault extends WorldObjectsMasterDefault implements
		PrizesMaster {

	private final GuiPrizes guiPrizes;

	public PrizesMasterDefault(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("PrizesMaster", tilesMaster, textureName, tileW, tileH);
		guiPrizes = new GuiPrizesDefault("prizes-atlas-medium", 25, 35, 550,
				-50);
	}

	@Override
	public void update(final float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public WorldObject factoryMethod(final Vector2 tilePos) {
		final Prize prize = new CubicPrize(atlasRows.get(0)[0], 0, this);
		prize.setRenderVector(new Vector2(
				-atlasRows.get(0)[0].getRegionWidth() / 2 - 3, -5));
		final Vector2 pos = new Vector2(tilePos);
		pos.add(new Vector2(0.5f, 0.5f));
		prize.setTilesPos(pos);
		prize.setVerticalPos(0.2f);

		prize.setName("prize");
		return prize;
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		for (final Vector2 pos : tilePositions) {
			addObject(factoryMethod(pos));
		}
	}

	@Override
	public void loadMapObjects(final CFMap cfMap) throws Exception {
		final List<Vector2> tilePositions = cfMap
				.getObjectTypeCoords(TiledObjectType.TILED_PRIZE);

		loadMapObjects(tilePositions);

	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

	@Override
	public void prizeHighlight(final Prize prize) {
		prize.setTextureRegion(atlasRows.get(1)[prize.getTexNum()]);
	}

	@Override
	public void prizeUnHighlight(final Prize prize) {
		prize.setTextureRegion(atlasRows.get(0)[prize.getTexNum()]);
	}

	@Override
	public GuiPrizes getGuiPrizes() {
		return guiPrizes;
	}

	@Override
	public void prizeCollected() {
		guiPrizes.prizeCollected();
	}

}
