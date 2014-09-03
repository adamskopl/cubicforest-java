package org.adamsko.cubicforest.gui.levels;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiElement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementLevel extends GuiElement {

	int levelIndex;
	
	public GuiElementLevel(int levelIndex, TextureRegion tr, int texNum,
			GuiElementsContainerDefault pareContainer, float posX, float posY) {
		super(tr, texNum, pareContainer, posX, posY);
		this.levelIndex = levelIndex;
	}
	
	public int getLevelIndex() {
		return levelIndex;
	}

}
