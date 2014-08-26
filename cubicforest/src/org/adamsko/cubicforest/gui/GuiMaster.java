package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.debug.GuiDebug;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.levels.GuiLevels;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class GuiMaster implements PickMasterClient {

	private List<GuiContainer> guiList;

	public List<GuiContainer> getGuiList() {
		return guiList;
	}

	private List<GuiMasterClient> clients;

	private GuiHeroTools guiHeroTools;
	private GuiOrders guiOrders;
	private GuiDebug guiDebug;
	private GuiLevels guiLevels;

	public GuiMaster(TilesMaster tilesMaster, MapsLoader mapsLoader) {
		guiList = new ArrayList<GuiContainer>();
		clients = new ArrayList<GuiMasterClient>();

		guiHeroTools = new GuiHeroTools(tilesMaster, "tools-atlas-medium", 40,
				42, 25, -50);

		guiOrders = new GuiOrders(tilesMaster, "orders-atlas-medium", 100, 100,
				20, -340);

		guiDebug = new GuiDebug(tilesMaster, "orders-atlas-medium", 50, 50,
				680, -100);

		guiLevels = new GuiLevels(mapsLoader, tilesMaster,
				"levels-atlas-medium", 30, 30, 600, -80);

		addGui(guiHeroTools);
		addGui(guiOrders);
		addGui(guiDebug);
		addGui(guiLevels);
	}

	public void addGui(GuiContainer newGui) {
		guiList.add(newGui);
	}

	public void addClient(GuiMasterClient newClient) {
		clients.add(newClient);
	}

	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {

		/*
		 * 
		 * 1. search for clicked GUI 2. pass clicked gui to clients
		 */
		for (GuiContainer guiContainer : guiList) {
			if (guiContainer.handleClick(inputScreenPos)) {
				clientsOnClicked(guiContainer);
			}
		}

	}

	private void clientsOnClicked(GuiContainer clickedContainer) {
		for (GuiMasterClient guiMasterClient : clients) {
			guiMasterClient.onGuiEvent(clickedContainer);
		}
	}

}
