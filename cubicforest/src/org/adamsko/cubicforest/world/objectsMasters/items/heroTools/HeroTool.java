package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class HeroTool extends ItemObject {

	HeroToolType_e heroToolType;
	HeroToolStates_e state;

	Integer buildCost;

	public HeroTool(TextureRegion tr, int texNum, HeroToolType_e heroToolType) {
		super(tr, texNum, ItemObjectType_e.ITEM_HERO_TOOL);

		this.heroToolType = heroToolType;

		this.buildCost = HeroesToolsMaster.heroTooltypeToCost(heroToolType);
		
		state = HeroToolStates_e.STATE_CONSTRUCTION;
		
	}

}
