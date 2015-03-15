package org.adamsko.cubicforest.gui.levels;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementLevel extends GuiElementDefault {

	int levelIndex;

	public GuiElementLevel(final int levelIndex,
			final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(objectsTextureChanger, tr, texNum, pareContainer, posX, posY);
		this.levelIndex = levelIndex;
	}

	public int getLevelIndex() {
		return levelIndex;
	}

}
