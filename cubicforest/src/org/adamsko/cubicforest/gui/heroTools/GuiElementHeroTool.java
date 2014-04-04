package org.adamsko.cubicforest.gui.heroTools;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementHeroTool extends GuiElement {

	private HeroToolType_e heroToolType;

	public GuiElementHeroTool(HeroToolType_e heroToolType, TextureRegion tr,
			int texNum, GuiContainer pareContainer, float posX, float posY) {
		super(tr, texNum, pareContainer, posX, posY);

		this.heroToolType = heroToolType;
		
	}

}
