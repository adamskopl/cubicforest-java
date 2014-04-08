package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class HeroTool extends ItemObject {

	HeroToolType_e heroToolType;
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

	public HeroTool(TextureRegion tr, int texNum, HeroToolType_e heroToolType) {
		super(tr, texNum, ItemObjectType_e.ITEM_HERO_TOOL);

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

}
