package org.adamsko.cubicforest.gui.orders;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;

public class GuiOrders extends GuiElementsContainerDefault {

	public GuiOrders(final float posX, final float posY) {
		super("gui orders", GuiType_e.GUI_ORDERS, posX, posY);
	}

	@Override
	public void createGui() {

		GuiElementOrder elementOrder = new GuiElementOrder(
				GuiOrdersType_e.ORDER_ACCEPT, this, 640, 0);
		elementOrder.setTexturesManager(getTexturesManager(), 10, 10);

		addGuiElement(elementOrder);

		elementOrder = new GuiElementOrder(GuiOrdersType_e.ORDER_CANCEL, this,
				0, 0);
		elementOrder.setTexturesManager(getTexturesManager(), 10, 10);

		addGuiElement(elementOrder);

	}

	@Override
	public void reload(final CFMap cfMap) {
	}

}
