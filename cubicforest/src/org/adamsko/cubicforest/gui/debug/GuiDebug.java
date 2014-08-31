package org.adamsko.cubicforest.gui.debug;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiType_e;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GuiDebug extends GuiContainer {

	public GuiDebug(final String textureName, final int tileW, final int tileH,
			final float posX, final float posY) {
		super("guiDebug", GuiType_e.GUI_DEBUG, textureName, tileW, tileH, posX,
				posY);
		createGui();
	}

	private void createGui() {

		final GuiElementDebug elementDebug = new GuiElementDebug(
				GuiDebugType_e.DEBUG_RELOAD, atlasRows.get(0)[0], 0, this, 0, 0);
		elementDebug.setRenderVector(new Vector2(0, 0));
		elementDebug.addLabel("R");
		elementDebug.altLabelLast(Color.WHITE, 1.0f, 20.0f, 32.0f);

		addGuiElement(elementDebug);

	}
}
