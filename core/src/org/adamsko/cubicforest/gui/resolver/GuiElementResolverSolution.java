package org.adamsko.cubicforest.gui.resolver;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementResolverSolution extends GuiElementResolver {

	int solutionIndex;

	public GuiElementResolverSolution(final int solutionIndex,
			final GuiResolverType guiResolverType,
			final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault parentContainer,
			final float posX, final float posY) {
		super(guiResolverType, objectsTextureChanger, tr, texNum,
				parentContainer, posX, posY);
		this.solutionIndex = solutionIndex;
	}

	public int getSolutionIndex() {
		return solutionIndex;
	}

}
