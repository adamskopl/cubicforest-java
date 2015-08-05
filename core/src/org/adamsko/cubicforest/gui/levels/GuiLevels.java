package org.adamsko.cubicforest.gui.levels;

import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.tiled.NullMapsLoader;

import com.badlogic.gdx.graphics.Color;

public class GuiLevels extends GuiElementsContainerDefault {

	private MapsLoader mapsLoader;

	public GuiLevels(final float posX, final float posY) {
		super("guiLevels", GuiType_e.GUI_LEVELS, posX, posY);
		mapsLoader = NullMapsLoader.instance();

		CLog.addObject(this, "GuiLevels");
		CLog.setUsage(this, true);
	}

	public void setMapsLoader(final MapsLoader mapsLoader) {
		this.mapsLoader = mapsLoader;
	}

	@Override
	public void createGui() {
		if (mapsLoader.isNull()) {
			CLog.error(this, "createGui mapsLoader null");
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
					this, posX, posY);
			guiElementLevel.setTexturesManager(getTexturesManager(), 5, 5,
					RenderableObjectVisualState.NORMAL);

			if (activeLevel == i) {
				higlightButton(guiElementLevel);
			}

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
		// e.setTextureRegion(atlasRows.get(1)[0]);
	}

	private void unhilightButtons() {
		for (final GuiElement e : guiElements) {
			// e.setTextureRegion(atlasRows.get(0)[0]);
		}
	}

	private void higlightButton(final GuiElement element) {
		// element.setTextureRegion(atlasRows.get(1)[0]);
	}

	@Override
	public void reload(final CFMap cfMap) {
	}
}
