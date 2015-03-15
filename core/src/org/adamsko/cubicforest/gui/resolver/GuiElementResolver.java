package org.adamsko.cubicforest.gui.resolver;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementResolver extends GuiElementDefault {

	private final GuiResolverType guiResolverType;

	public GuiElementResolver(final GuiResolverType guiResolverType,
			final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault parentContainer,
			final float posX, final float posY) {
		super(objectsTextureChanger, tr, texNum, parentContainer, posX, posY);
		this.guiResolverType = guiResolverType;
	}

	public GuiResolverType getGuiResolverType() {
		return guiResolverType;
	}

}
