package org.adamsko.cubicforest.gui.orders;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class GuiOrders extends GuiContainer {

	public GuiOrders(TilesMaster TM, String textureName, int tileW, int tileH,
			float posX, float posY) {
		super("gui orders", TM, GuiType_e.GUI_ORDERS, textureName, tileW, tileH, posX, posY);
		createGui();
	}

	private void createGui() {

		GuiElementOrder elementOrder = new GuiElementOrder(
				GuiOrdersType_e.ORDER_ACCEPT, atlasRows.get(1)[0], 0, this, 640,
				0);
		elementOrder.setRenderVector(new Vector2(0, -100));
		
		addGuiElement(elementOrder);
		
		elementOrder = new GuiElementOrder(
				GuiOrdersType_e.ORDER_CANCEL, atlasRows.get(1)[1], 1, this, 0,
				0);
		elementOrder.setRenderVector(new Vector2(0, -100));

		addGuiElement(elementOrder);

	}

}
