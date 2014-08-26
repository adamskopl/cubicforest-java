package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType;
import org.adamsko.cubicforest.world.tile.TilesMaster.TileEvent;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class HeroTool extends ItemObject {

	HeroToolType heroToolType;

	public HeroToolType getHeroToolType() {
		return heroToolType;
	}

	HeroToolStates_e toolState;

	public void setState(final HeroToolStates_e state) {
		this.toolState = state;
	}

	public HeroToolStates_e getToolState() {
		return toolState;
	}

	final int buildCost;

	public int getBuildCost() {
		return buildCost;
	}

	public HeroTool(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container,
			final HeroToolType heroToolType) {
		super(tr, texNum, container, ItemObjectType.ITEM_HERO_TOOL);

		this.heroToolType = heroToolType;

		this.buildCost = HeroesToolsMaster.heroTooltypeToCost(heroToolType);

		toolState = HeroToolStates_e.STATE_CONSTRUCTION;

	}

	@Override
	protected void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTilePathSearchValid(true);
	}

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

	/**
	 * Process tile collision with given object. Return order result for that
	 * object.
	 * 
	 * @param entityObject
	 * @param eventType
	 * @return
	 */
	public abstract void onEntityTileEvent(CollisionResult collisionResult,
			EntityObject entityObject, TileEvent eventType);

}
