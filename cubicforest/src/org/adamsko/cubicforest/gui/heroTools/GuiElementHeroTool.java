package org.adamsko.cubicforest.gui.heroTools;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementHeroTool extends GuiElement {

	private HeroToolType heroToolType;

	public HeroToolType getHeroToolType() {
		return heroToolType;
	}

	public GuiElementHeroTool(HeroToolType heroToolType, TextureRegion tr,
			int texNum, GuiContainer pareContainer, float posX, float posY) {
		super(tr, texNum, pareContainer, posX, posY);

		this.heroToolType = heroToolType;
		
	}

}
