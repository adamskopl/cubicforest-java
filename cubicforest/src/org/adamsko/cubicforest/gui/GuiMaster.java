package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;

import com.badlogic.gdx.math.Vector2;

public class GuiMaster implements PickMasterClient {

	private List<Gui> guiList;
	private List<GuiMasterClient> clients;

	public void addGui(Gui newGui) {
		guiList.add(newGui);
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
		
	}
	
}
