package org.adamsko.cubicforest.gui.debug;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiElement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementDebug extends GuiElement {

	GuiDebugType_e debugType;

	public GuiElementDebug(GuiDebugType_e debugType, TextureRegion tr, int texNum,
			GuiContainer pareContainer, float posX, float posY) {
		super(tr, texNum, pareContainer, posX, posY);
		
		this.debugType = debugType;
	}
	
	public GuiDebugType_e getDebugType() {
		return debugType;
	}

}
