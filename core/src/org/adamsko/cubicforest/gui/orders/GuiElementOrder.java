package org.adamsko.cubicforest.gui.orders;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementOrder extends GuiElementDefault {

	GuiOrdersType_e orderType;

	public GuiElementOrder(final GuiOrdersType_e orderType,
			final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(objectsTextureChanger, tr, texNum, pareContainer, posX, posY);

		this.orderType = orderType;
	}

	public GuiOrdersType_e getOrderType() {
		return orderType;
	}

}
