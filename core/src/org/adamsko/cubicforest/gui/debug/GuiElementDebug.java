package org.adamsko.cubicforest.gui.debug;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementDebug extends GuiElementDefault {

	GuiDebugType debugType;

	public GuiElementDebug(final GuiDebugType debugType,
			final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(objectsTextureChanger, tr, texNum, pareContainer, posX, posY);

		this.debugType = debugType;
	}

	public GuiDebugType getDebugType() {
		return debugType;
	}

}
