package org.adamsko.cubicforest.gui.resolver;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementResolver extends GuiElementDefault {

	private final GuiResolverType guiResolverType;

	public GuiElementResolver(final GuiResolverType guiResolverType,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault parentContainer,
			final float posX, final float posY) {
		super(tr, texNum, parentContainer, posX, posY);
		this.guiResolverType = guiResolverType;
	}

	public GuiResolverType getGuiResolverType() {
		return guiResolverType;
	}

}
