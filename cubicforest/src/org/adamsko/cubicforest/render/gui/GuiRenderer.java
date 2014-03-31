package org.adamsko.cubicforest.render.gui;

import java.util.ArrayList;
import java.util.List;

public class GuiRenderer {

	List<GuiElementsContainer> guiElementsContainers;
	
	public GuiRenderer() {
		guiElementsContainers = new ArrayList<GuiElementsContainer>();
	}
	
	public void addGEC(GuiElementsContainer guiElementsContainer) {
		guiElementsContainers.add(guiElementsContainer);
	}
	
	private void renderGECs() {
		
	}
	
	private void renderGuiElement(GuiElement guiElement) {
		
	}
	
}
