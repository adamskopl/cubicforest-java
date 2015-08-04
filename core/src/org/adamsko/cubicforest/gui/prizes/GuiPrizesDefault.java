package org.adamsko.cubicforest.gui.prizes;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;

import com.badlogic.gdx.Gdx;

public class GuiPrizesDefault extends GuiElementsContainerDefault implements
GuiPrizes {

	private int prizesCollected;
	// holds all gui elements representing collected prizes. when prize is
	// collected, gui elements from this list is added
	private List<GuiElementPrize> availablePrizes;

	public GuiPrizesDefault(final String textureName, final int tileW,
			final int tileH, final float posX, final float posY) {
		super("guiHeroTools", GuiType_e.GUI_HERO_TOOLS, textureName, tileW,
				tileH, posX, posY);
		prizesCollected = 0;
	}

	@Override
	public void reload(final CFMap cfMap) {
		prizesCollected = 0;
		clearGuiElements();
	}

	@Override
	public void prizeCollected() {
		prizesCollected++;
		if (prizesCollected > availablePrizes.size()) {
			Gdx.app.error("GuiPrizesDefault::prizeCollected()",
					"maximum number of prizes exceeded");
			return;
		}

		addGuiElement(availablePrizes.get(prizesCollected - 1));

	}

	@Override
	public void setCollectedPrizesNumber(final int prizesCollected) {
		this.prizesCollected = 0;
		clearGuiElements();
		for (int i = 0; i < prizesCollected; i++) {
			prizeCollected();
		}
	};

	@Override
	public void createGui() {

		availablePrizes = new ArrayList<GuiElementPrize>();
		final int prizesNumber = 3;

		for (int i = 0; i < prizesNumber; i++) {
			final GuiElementPrize guiElementPrize = new GuiElementPrize(
					atlasRows.get(0)[0], 0, this, i * 25 + 5, 0);
			availablePrizes.add(guiElementPrize);
		}

	}
}
