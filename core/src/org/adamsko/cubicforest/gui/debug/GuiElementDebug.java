package org.adamsko.cubicforest.gui.debug;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementDebug extends GuiElementDefault {

	GuiDebugType debugType;

	public GuiElementDebug(final GuiDebugType debugType,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(tr, texNum, pareContainer, posX, posY);

		this.debugType = debugType;
	}

	public GuiDebugType getDebugType() {
		return debugType;
	}

}
