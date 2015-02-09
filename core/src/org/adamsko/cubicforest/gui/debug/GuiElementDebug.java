package org.adamsko.cubicforest.gui.debug;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiElementDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementDebug extends GuiElementDefault {

	GuiDebugType debugType;

	public GuiElementDebug(GuiDebugType debugType, TextureRegion tr, int texNum,
			GuiElementsContainerDefault pareContainer, float posX, float posY) {
		super(tr, texNum, pareContainer, posX, posY);
		
		this.debugType = debugType;
	}
	
	public GuiDebugType getDebugType() {
		return debugType;
	}

}
