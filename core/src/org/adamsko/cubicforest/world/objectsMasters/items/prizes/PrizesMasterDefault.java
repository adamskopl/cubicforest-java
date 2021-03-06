package org.adamsko.cubicforest.world.objectsMasters.items.prizes;

import java.util.List;

import org.adamsko.cubicforest.gui.prizes.GuiPrizes;
import org.adamsko.cubicforest.gui.prizes.GuiPrizesDefault;
import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMemento;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TileDirection;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PrizesMasterDefault extends WorldObjectsMasterDefault implements
		PrizesMaster {

	private final GuiPrizes guiPrizes;
	/**
	 * Starting number of prizes.
	 */
	private int prizesStartNumber;
	/**
	 * Number of collected prizes.
	 */
	private int prizesCollected;

	public PrizesMasterDefault(final TilesMaster tilesMaster) {
		super("PrizesMaster", "prize", tilesMaster);
		guiPrizes = new GuiPrizesDefault(550, -50);
		this.prizesStartNumber = 0;
		this.prizesCollected = 0;

		CLog.addObject(this, "PrizesMasterDefault");
		CLog.setUsage(this, true);
	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public WorldObject factoryMethod(final Vector2 tilePos) {
		final Prize prize = new CubicPrize(this);
		prize.setTexturesManager(getTexturesManager(), WorldObjectType.PRIZE);
		prize.setRenderVector(new Vector2(-35.0f, -23.0f));
		final Vector2 pos = new Vector2(tilePos);
		pos.add(new Vector2(0.5f, 0.5f));
		prize.setTilesPos(pos);
		prize.setVerticalPos(0.2f);

		prize.setName("prize");

		prize.tileDirection().changeDirection(
				TileDirection.randomTileDirection());

		return prize;
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		prizesCollected = 0;
		for (final Vector2 pos : tilePositions) {
			addObject(factoryMethod(pos));
		}
	}

	@Override
	public void loadMapObjects(final CFMap cfMap) throws Exception {
		final List<Vector2> tilePositions = cfMap
				.getObjectTypeCoords(TiledObjectType.TILED_PRIZE);

		loadMapObjects(tilePositions);
		prizesStartNumber = getWorldObjects().size();
	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

	@Override
	public void prizeHighlight(final Prize prize) {
		// prize.setTextureRegion(atlasRows.get(1)[prize.getTexNum()]);
	}

	@Override
	public void prizeUnHighlight(final Prize prize) {
		// prize.setTextureRegion(atlasRows.get(0)[prize.getTexNum()]);
	}

	@Override
	public GuiPrizes getGuiPrizes() {
		return this.guiPrizes;
	}

	@Override
	public void onPrizeCollected() {
		this.prizesCollected++;
		this.guiPrizes.prizeCollected();
	}

	@Override
	public int prizesCollected() {
		return this.prizesCollected;
	}

	@Override
	public boolean allPrizesCollected() {
		return this.prizesCollected == this.prizesStartNumber;
	}

	@Override
	public void setMemento(final WOMMemento memento) {
		super.setMemento(memento);
		this.prizesCollected = prizesStartNumber - getWorldObjects().size();
		guiPrizes.setCollectedPrizesNumber(this.prizesCollected);
		if (this.prizesCollected < 0) {
			Gdx.app.error("PrizesMasterDefault::setMemento()",
					"prizesCollected < 0");
		}
		CLog.debug(
				this,
				"setMemento, collected:"
						+ Integer.toString(this.prizesCollected));
	}
}
