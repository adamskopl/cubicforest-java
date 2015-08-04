package org.adamsko.cubicforest.gui.orders;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;

public class GuiElementOrder extends GuiElementDefault {

	GuiOrdersType_e orderType;

	public GuiElementOrder(final GuiOrdersType_e orderType,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(pareContainer, posX, posY);

		this.orderType = orderType;
	}

	public GuiOrdersType_e getOrderType() {
		return orderType;
	}

}
