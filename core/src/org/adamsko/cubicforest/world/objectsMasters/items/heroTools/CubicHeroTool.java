package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class CubicHeroTool extends CubicObject implements HeroTool {

	final int buildCost;
	HeroToolStates_e toolState;

	/**
	 * For NullHeroTool
	 */
	CubicHeroTool() {
		super();
		buildCost = 0;
	}

	public CubicHeroTool(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container,
			final WorldObjectType worldObjectType) {
		super(tr, texNum, container, worldObjectType);

		this.buildCost = HeroesToolsMasterDefault
				.heroTooltypeToCost(worldObjectType);

		toolState = HeroToolStates_e.STATE_CONSTRUCTION;

	}

	@Override
	public void setState(final HeroToolStates_e state) {
		this.toolState = state;
	}

	@Override
	public HeroToolStates_e getToolState() {
		return toolState;
	}

	@Override
	public int getBuildCost() {
		return buildCost;
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTilePathSearchValid(true);
		getTilePropertiesIndicator().setTileHeroesRangeValid(true);
		getTilePropertiesIndicator().setTileHighlightedAsOccupied(false);
	}

	@Override
	public void changeState(final HeroToolStates_e newState) {
		switch (newState) {
		case STATE_CONSTRUCTION:
			newStateConstruction();
			break;
		case STATE_READY:
			newStateReady();
			break;
		default:
			break;
		}

		this.toolState = newState;
	}

	private void newStateConstruction() {
	}

	private void newStateReady() {
		switch (toolState) {
		case STATE_CONSTRUCTION:

			break;

		default:
			break;
		}
	}

}
