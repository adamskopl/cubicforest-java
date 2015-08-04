package org.adamsko.cubicforest.gui.resolver;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

public class GuiElementResolver extends GuiElementDefault {

	private final GuiResolverType guiResolverType;

	public GuiElementResolver(final GuiResolverType guiResolverType,
			final GuiElementsContainerDefault parentContainer,
			final float posX, final float posY) {
		super(parentContainer, posX, posY);
		this.guiResolverType = guiResolverType;
	}

	public GuiResolverType getGuiResolverType() {
		return guiResolverType;
	}

}
