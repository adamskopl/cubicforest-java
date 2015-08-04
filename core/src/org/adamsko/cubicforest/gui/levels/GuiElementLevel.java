package org.adamsko.cubicforest.gui.levels;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

public class GuiElementLevel extends GuiElementDefault {

	int levelIndex;

	public GuiElementLevel(final int levelIndex,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(pareContainer, posX, posY);
		this.levelIndex = levelIndex;
	}

	public int getLevelIndex() {
		return levelIndex;
	}

}
