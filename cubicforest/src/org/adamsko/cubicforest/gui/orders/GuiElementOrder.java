package org.adamsko.cubicforest.gui.orders;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiElement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementOrder extends GuiElement {

	GuiOrdersType_e orderType;
	
	public GuiElementOrder(GuiOrdersType_e orderType, TextureRegion tr, int texNum,
			GuiContainer pareContainer, float posX, float posY) {
		super(tr, texNum, pareContainer, posX, posY);
		
		this.orderType = orderType;
	}

	public GuiOrdersType_e getOrderType() {
		return orderType;
	}

}
