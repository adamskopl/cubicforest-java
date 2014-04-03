package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GuiMaster implements PickMasterClient {

	private List<GuiContainer> guiList;
	private List<GuiMasterClient> clients;

	public void addGui(GuiContainer newGui) {
		guiList = new ArrayList<GuiContainer>();
		clients = new ArrayList<GuiMasterClient>();
		
		guiList.add(newGui);
	}
	
	public void addClient(GuiMasterClient newClient) {
		clients.add(newClient);
	}
	
	@Override
	public void onInput(Vector2 inputScreenPos, Vector2 inputTilesPos) {

		
		/*
		 * 
		 * 1. search for clicked GUI
		 * 2. pass clicked gui to clients
		 * 
		 * 
		 */
		for(GuiContainer guiContainer : guiList) {
			if(guiContainer.isClicked(inputScreenPos)) {
				clientsOnClicked(guiContainer);
			}
		}
		
	}
	
	private void clientsOnClicked(GuiContainer clickedContainer) {
		for(GuiMasterClient guiMasterClient : clients) {
			guiMasterClient.onGuiEvent(clickedContainer);
		}
	}
	
}
