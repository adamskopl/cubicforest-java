package org.adamsko.cubicforest.gui.orders;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiElementDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementOrder extends GuiElementDefault {

	GuiOrdersType_e orderType;
	
	public GuiElementOrder(GuiOrdersType_e orderType, TextureRegion tr, int texNum,
			GuiElementsContainerDefault pareContainer, float posX, float posY) {
		super(tr, texNum, pareContainer, posX, posY);
		
		this.orderType = orderType;
	}

	public GuiOrdersType_e getOrderType() {
		return orderType;
	}

}
