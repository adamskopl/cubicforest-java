package org.adamsko.cubicforest.gui.resolver;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementResolverSolution extends GuiElementResolver {

	int solutionIndex;

	public GuiElementResolverSolution(final int solutionIndex,
			final GuiResolverType guiResolverType, final TextureRegion tr,
			final int texNum,
			final GuiElementsContainerDefault parentContainer,
			final float posX, final float posY) {
		super(guiResolverType, tr, texNum, parentContainer, posX, posY);
		this.solutionIndex = solutionIndex;
	}

	public int getSolutionIndex() {
		return solutionIndex;
	}

}
