package org.adamsko.cubicforest.gui.orders;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;

public class GuiOrders extends GuiElementsContainerDefault {

	public GuiOrders(final float posX, final float posY) {
		super("gui orders", GuiType_e.GUI_ORDERS, posX, posY);
	}

	@Override
	public void createGui() {

		GuiElementOrder elementOrder = new GuiElementOrder(
				GuiOrdersType_e.ORDER_ACCEPT, this, 640, 0);
		elementOrder.setTexturesManager(getTexturesManager(), 25, 25,
				RenderableObjectVisualState.ALLOWED);

		addGuiElement(elementOrder);

		elementOrder = new GuiElementOrder(GuiOrdersType_e.ORDER_CANCEL, this,
				0, 0);
		elementOrder.setTexturesManager(getTexturesManager(), 25, 25,
				RenderableObjectVisualState.SELECTED_FAIL);

		addGuiElement(elementOrder);

	}

	@Override
	public void reload(final CFMap cfMap) {
	}

}
