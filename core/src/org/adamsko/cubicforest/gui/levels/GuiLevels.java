package org.adamsko.cubicforest.gui.levels;

import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GuiLevels extends GuiElementsContainerDefault {

	public GuiLevels(final MapsLoader mapsLoader, final String textureName,
			final int tileW, final int tileH, final float posX, final float posY) {
		super("guiLevels", GuiType_e.GUI_LEVELS, textureName, tileW, tileH,
				posX, posY);
		createGui(mapsLoader);
	}

	private void createGui(final MapsLoader mapsLoader) {
		if (mapsLoader.isNull()) {
			Gdx.app.error("GuiLevels::createGui()", "mapsLoader.isNull()");
			return;
		}
		final int levelsNumber = mapsLoader.size();
		final int activeLevel = mapsLoader.getMapActiveIndex();

		int posX = 0;
		int posY = 40;
		for (int i = 0; i < levelsNumber; i++) {
			if (i % 2 == 0) {
				posX = 0;
				posY -= 40;
			}

			final GuiElementLevel guiElementLevel = new GuiElementLevel(i,
					getObjectsTextureChanger(), atlasRows.get(0)[0], 0, this,
					posX, posY);

			if (activeLevel == i) {
				higlightButton(guiElementLevel);
			}

			guiElementLevel.setRenderVector(new Vector2(0, 0));
			guiElementLevel.addLabel(i + 1);
			guiElementLevel.altLabelLast(Color.WHITE, 1.0f, 12.0f, 22.0f);
			addGuiElement(guiElementLevel);

			posX += 40;
		}

	}

	@Override
	protected void guiElementClicked(final GuiElement clickedElement) {
		unhilightButtons();
		higlightButton(clickedElement);
	}

	public void levelClicked(final int levelIndex) {
		final GuiElement e = guiElements.get(levelIndex);
		e.setTextureRegion(atlasRows.get(1)[0]);
	}

	private void unhilightButtons() {
		for (final GuiElement e : guiElements) {
			e.setTextureRegion(atlasRows.get(0)[0]);
		}
	}

	private void higlightButton(final GuiElement element) {
		element.setTextureRegion(atlasRows.get(1)[0]);
	}

	@Override
	public void reload(final CFMap cfMap) {
	}
}
