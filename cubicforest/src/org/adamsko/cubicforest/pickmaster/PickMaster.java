package org.adamsko.cubicforest.pickmaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.CoordCalc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PickMaster {
	private List<PickMasterClient> clients;
	
	public PickMaster() {
		clients = new ArrayList<PickMasterClient>();
	}
	
	public void addClient(PickMasterClient client) {
		clients.add(client);
	}
	
	public void update() {
		if(Gdx.input.isTouched()) {
			Vector2 screenPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
			Gdx.app.log("INPUT: ",  Gdx.input.getX() + ", " + Gdx.input.getY());
			Vector2 tilesPos = new Vector2(CoordCalc.screenToTiles(screenPos));
			for(PickMasterClient client : clients) {				
				client.onInput(screenPos, tilesPos);
			}
		}
	}
}
