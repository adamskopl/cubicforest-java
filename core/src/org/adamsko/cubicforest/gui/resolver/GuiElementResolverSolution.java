package org.adamsko.cubicforest.gui.resolver;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

public class GuiElementResolverSolution extends GuiElementResolver {

	int solutionIndex;

	public GuiElementResolverSolution(final int solutionIndex,
			final GuiResolverType guiResolverType,
			final GuiElementsContainerDefault parentContainer,
			final float posX, final float posY) {
		super(guiResolverType, parentContainer, posX, posY);
		this.solutionIndex = solutionIndex;
	}

	public int getSolutionIndex() {
		return solutionIndex;
	}

}
