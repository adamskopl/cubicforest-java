package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class HeroTool extends ItemObject {

	HeroToolType heroToolType;

	public HeroToolType getHeroToolType() {
		return heroToolType;
	}

	HeroToolStates_e state;

	public void setState(HeroToolStates_e state) {
		this.state = state;
	}

	public HeroToolStates_e getState() {
		return state;
	}

	Integer buildCost;

	public Integer getBuildCost() {
		return buildCost;
	}

	public HeroTool(TextureRegion tr, int texNum, HeroToolType heroToolType) {
		super(tr, texNum, ItemObjectType.ITEM_HERO_TOOL);

		this.heroToolType = heroToolType;

		this.buildCost = HeroesToolsMaster.heroTooltypeToCost(heroToolType);

		state = HeroToolStates_e.STATE_CONSTRUCTION;

	}

	public void changeState(HeroToolStates_e newState) {
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

		this.state = newState;
	}

	private void newStateConstruction() {

	}

	private void newStateReady() {
		switch (state) {
		case STATE_CONSTRUCTION:

			break;

		default:
			break;
		}
	}

	/**
	 * Process tile interaction with given object. Return order result for that
	 * object.
	 * 
	 * @param entityObject
	 * @param eventType
	 * @return
	 */
	public abstract void onEntityTileEvent(InteractionResult interactionResult,
			EntityObject entityObject, TileEvent eventType);

}
