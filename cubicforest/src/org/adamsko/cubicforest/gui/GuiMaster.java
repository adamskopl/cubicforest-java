package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesCounter;
import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GuiMaster implements PickMasterClient {

	private List<GuiContainer> guiList;

	public List<GuiContainer> getGuiList() {
		return guiList;
	}

	private List<GuiMasterClient> clients;

	private GuiHeroTools guiHeroTools;
	private GuiOrders guiOrders;

	public GuiMaster(TilesMaster tilesMaster) {
		guiList = new ArrayList<GuiContainer>();
		clients = new ArrayList<GuiMasterClient>();

		guiHeroTools = new GuiHeroTools(tilesMaster, "tools-atlas-medium", 40,
				45, 25, -50);

		guiOrders = new GuiOrders(tilesMaster, "orders-atlas-medium", 100, 100,
				20, -340);

		addGui(guiHeroTools);
		addGui(guiOrders);
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
			if (guiContainer.isClicked(inputScreenPos)) {
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
