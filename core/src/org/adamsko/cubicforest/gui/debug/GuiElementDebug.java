package org.adamsko.cubicforest.gui.debug;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

public class GuiElementDebug extends GuiElementDefault {

	GuiDebugType debugType;

	public GuiElementDebug(final GuiDebugType debugType,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(pareContainer, posX, posY);

		this.debugType = debugType;
	}

	public GuiDebugType getDebugType() {
		return debugType;
	}

}
