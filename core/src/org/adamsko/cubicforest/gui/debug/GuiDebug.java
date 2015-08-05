package org.adamsko.cubicforest.gui.debug;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;

import com.badlogic.gdx.graphics.Color;

public class GuiDebug extends GuiElementsContainerDefault {

	public GuiDebug(final float posX, final float posY) {
		super("guiDebug", GuiType_e.GUI_DEBUG, posX, posY);
	}

	@Override
	public void createGui() {

		final GuiElementDebug elementDebug = new GuiElementDebug(
				GuiDebugType.DEBUG_RELOAD, this, 0, 0);
		elementDebug.setTexturesManager(getTexturesManager(), 5, 5,
				RenderableObjectVisualState.NORMAL);
		elementDebug.addLabel("R");
		elementDebug.altLabelLast(Color.WHITE, 1.0f, 20.0f, 32.0f);

		addGuiElement(elementDebug);

	}

	@Override
	public void reload(final CFMap cfMap) {
	}
}
